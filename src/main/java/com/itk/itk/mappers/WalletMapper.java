package com.itk.itk.mappers;

import com.itk.itk.dto.WalletDTO;
import com.itk.itk.models.Wallet;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class WalletMapper {

    private final ModelMapper modelMapper;

    public WalletDTO toDTO (Wallet wallet) {
        return modelMapper.map(wallet, WalletDTO.class);
    }
}
