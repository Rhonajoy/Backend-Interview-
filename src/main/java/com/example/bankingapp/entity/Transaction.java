package com.example.bankingapp.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Entity
@Data
public class Transaction {
    @Id
    @GeneratedValue
   private Long transactionRef;
    @NotNull(message = "Transaction amount is required")
    private BigDecimal amount;
    @NotBlank(message = "narration   is mandatory")
    private String narration;
    @NotNull
    @Enumerated(EnumType.STRING)
   private TransactionStatus status;
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "customer_id",nullable = false)
   private Customer customer;
   private LocalDateTime transactionTime = LocalDateTime.now();

}
