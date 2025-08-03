package com.itk.itk.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.itk.itk.models.OperationType;
import jakarta.validation.constraints.DecimalMin;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

import java.math.BigDecimal;
import java.util.UUID;

@Data
public class WalletOperationRequest {
    @NotNull(message = "Wallet ID is required")
    @JsonProperty("walletId")
    private UUID walletId;

    @NotNull(message = "Operation type is required")
    private OperationType operationType;

    @NotNull(message = "Amount is required")
    @DecimalMin(value = "0.01", message = "Amount must be greater than 0")
    private BigDecimal amount;
}
