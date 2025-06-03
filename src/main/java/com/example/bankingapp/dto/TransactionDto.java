package com.example.bankingapp.dto;

import com.example.bankingapp.entity.TransactionStatus;
import jakarta.validation.constraints.DecimalMin;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

import java.math.BigDecimal;

@Data
public class TransactionDto {
    @NotNull(message = "Amount is required")
    @DecimalMin(value = "0.01",inclusive = true,message = "Amount must be greater than 0")
    private BigDecimal amount;
    @NotNull(message = "Transaction status is required")
    private TransactionStatus status;
    @NotNull(message=" Customer ID is required")
    private Long customerId;

}
