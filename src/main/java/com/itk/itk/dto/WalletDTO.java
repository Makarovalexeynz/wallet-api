package com.itk.itk.dto;

import lombok.*;

import java.math.BigDecimal;
import java.util.UUID;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class WalletDTO {

    private UUID walletId;

    private BigDecimal balance;
}
