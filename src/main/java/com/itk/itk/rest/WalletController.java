package com.itk.itk.rest;

import com.itk.itk.dto.WalletDTO;
import com.itk.itk.dto.WalletOperationRequest;
import com.itk.itk.dto.WalletResponse;
import com.itk.itk.services.WalletService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.UUID;

@RestController
@RequiredArgsConstructor
@Slf4j
public class WalletController {

    private final WalletService walletService;

    @GetMapping(path = "api/v1/wallets/{id}")
    public ResponseEntity<WalletDTO> getWallet(@PathVariable UUID id) {
        log.info("Getting balance for wallet ID: {}", id);
        WalletDTO walletDTO = walletService.getBalance(id);
        return ResponseEntity.ok(walletDTO);
    }

    @PostMapping(path = "api/v1/wallet")
    public ResponseEntity<WalletResponse> processWalletOperation(
            @Valid @RequestBody WalletOperationRequest request) {
        log.info("Processing wallet operation: {}", request);
        return ResponseEntity.ok(walletService.processOperation(request));
    }
}
