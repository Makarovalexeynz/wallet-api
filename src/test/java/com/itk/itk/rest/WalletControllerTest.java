package com.itk.itk.rest;

import com.itk.itk.dto.WalletDTO;
import com.itk.itk.dto.WalletOperationRequest;
import com.itk.itk.dto.WalletResponse;
import com.itk.itk.mappers.WalletMapper;
import com.itk.itk.models.OperationType;
import com.itk.itk.services.WalletService;
import org.junit.jupiter.api.Test;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.http.MediaType;
import java.math.BigDecimal;
import java.util.UUID;
import com.fasterxml.jackson.databind.ObjectMapper;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;
import com.itk.itk.exceptions.NotFoundException;

@WebMvcTest(WalletController.class)
public class WalletControllerTest {

    @MockBean
    private ModelMapper modelMapper;

    @Autowired
    private ObjectMapper objectMapper;

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private WalletService walletService;

    @MockBean
    private WalletMapper walletMapper;

    @Test
    void getWallet_shouldReturn200() throws Exception {
        UUID walletId = UUID.randomUUID();
        BigDecimal balance = BigDecimal.valueOf(123);
        WalletDTO mockWalletDTO = new WalletDTO();
        mockWalletDTO.setWalletId(walletId);
        mockWalletDTO.setBalance(balance);

        when(walletService.getBalance(walletId)).thenReturn(mockWalletDTO);

        mockMvc.perform(get("/api/v1/wallets/{WALLET_UUID}", walletId)
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.walletId").value(walletId.toString()))
                .andExpect(jsonPath("$.balance").value(123.0));
    }

    @Test
    void getWallet_shouldReturn404WhenNotFound() throws Exception {

        UUID walletId = UUID.randomUUID();
        when(walletService.getBalance(walletId))
                .thenThrow(new NotFoundException("Not found"));

        mockMvc.perform(get("/api/v1/wallets/{WALLET_UUID}", walletId))
                .andExpect(status().isNotFound());
    }

    @Test
    void processWalletOperation_shouldReturn200AndResponse() throws Exception {

        WalletOperationRequest request = new WalletOperationRequest();
        UUID testUUID = UUID.randomUUID();
        BigDecimal testAmount = BigDecimal.valueOf(100);
        OperationType testOperationType = OperationType.DEPOSIT;
        request.setWalletId(testUUID);
        request.setAmount(testAmount);
        request.setOperationType(testOperationType);

        WalletResponse mockWalletResponse = new WalletResponse();
        mockWalletResponse.setWalletId(testUUID);
        mockWalletResponse.setBalance(testAmount);

        when(walletService.processOperation(any(WalletOperationRequest.class)))
                .thenReturn(mockWalletResponse);

        mockMvc.perform(post("/api/v1/wallet")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(request)))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.walletId").value(mockWalletResponse.getWalletId().toString()))
                .andExpect(jsonPath("$.balance").value(100.0));
    }

    @Test
    void processWalletOperation_shouldReturn400ForInvalidRequest() throws Exception {

        WalletOperationRequest invalidRequest = new WalletOperationRequest();

        mockMvc.perform(post("/api/v1/wallet")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(invalidRequest)))
                .andExpect(status().isBadRequest());
    }
}
