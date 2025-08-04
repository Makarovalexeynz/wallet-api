package com.itk.itk.services;

import com.itk.itk.dto.WalletOperationRequest;
import com.itk.itk.dto.WalletDTO;
import com.itk.itk.dto.WalletResponse;
import com.itk.itk.exceptions.NotFoundException;

import java.util.UUID;

public interface WalletService {
    WalletDTO getBalance(UUID walletId) throws NotFoundException;

    WalletResponse processOperation(WalletOperationRequest request) throws NotFoundException;
}
