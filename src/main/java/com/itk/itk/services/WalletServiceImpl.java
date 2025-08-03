package com.itk.itk.services;

import com.itk.itk.dto.WalletDTO;
import com.itk.itk.dto.WalletOperationRequest;
import com.itk.itk.dto.WalletResponse;
import com.itk.itk.exceptions.InsufficientFundsException;
import com.itk.itk.exceptions.NotFoundException;
import com.itk.itk.mappers.WalletMapper;
import com.itk.itk.models.Transaction;
import com.itk.itk.models.Wallet;
import com.itk.itk.repositories.TransactionRepository;
import com.itk.itk.repositories.WalletRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.util.UUID;

@Service
@RequiredArgsConstructor
@Slf4j
public class WalletServiceImpl implements WalletService {

    private final WalletRepository walletRepository;

    private final TransactionRepository transactionRepository;

    private final WalletMapper walletMapper;

    @Override
    @Transactional(readOnly = true)
    public WalletDTO getBalance(UUID walletId) {
        log.debug("Fetching balance for wallet ID: {}", walletId);
        return walletRepository.findById(walletId)
                .map(walletMapper::toDTO)
                .orElseThrow(() -> new NotFoundException("Wallet not found with ID: " + walletId));
    }

    @Override
    @Transactional
    public WalletResponse processOperation(WalletOperationRequest request) {
        log.info("Processing wallet operation: {}", request);

        if(request.getAmount().compareTo(BigDecimal.ZERO) <=0){
            throw new IllegalArgumentException("Amount must be positive");
        }

        Wallet wallet = walletRepository.findByIdForUpdate(request.getWalletId())
                .orElseThrow(() -> new NotFoundException("Wallet not found with ID: "+ request.getWalletId()));


        switch (request.getOperationType()) {
            case DEPOSIT:
                wallet.setBalance(wallet.getBalance().add(request.getAmount()));
                break;
            case WITHDRAW:
                if (wallet.getBalance().compareTo(request.getAmount()) < 0) {
                    throw new InsufficientFundsException("Insufficient funds for withdrawal. Current balance: "
                            + wallet.getBalance());
                }
                wallet.setBalance(wallet.getBalance().subtract(request.getAmount()));
                break;
            default:
                throw new UnsupportedOperationException("Unsupported operation type: "
                        + request.getOperationType());
        }

        Transaction transaction = new Transaction();
        transaction.setWallet(wallet);
        transaction.setOperationType(request.getOperationType());
        transaction.setAmount(request.getAmount());
        transactionRepository.save(transaction);

        Wallet savedWallet = walletRepository.save(wallet);

        return new WalletResponse(savedWallet.getId(), savedWallet.getBalance());
    }
}
