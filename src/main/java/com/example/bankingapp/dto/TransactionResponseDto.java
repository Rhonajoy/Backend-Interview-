package com.example.bankingapp.dto;

import com.example.bankingapp.entity.TransactionStatus;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;
import java.time.LocalDateTime;
@Getter
@Setter
public class TransactionResponseDto {

    private Long transactionRef;
    @NotNull(message = "Transaction amount is required")
    private BigDecimal amount;
    @NotNull
    @Enumerated(EnumType.STRING)
    private TransactionStatus status;
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "customer_id",nullable = false)
   private Long customerId;
    @NotNull(message = "")
    private  String name;
    private LocalDateTime transactionTime = LocalDateTime.now();

}
