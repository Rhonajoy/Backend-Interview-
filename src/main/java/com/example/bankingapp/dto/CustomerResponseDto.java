package com.example.bankingapp.dto;

import jakarta.persistence.Column;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.validation.constraints.DecimalMin;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;
import java.util.List;

@Getter
@Setter
public class CustomerResponseDto {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private  Long id;

    @Column(unique = true,nullable = false)
    @NotBlank(message = "Account Number is mandatory")
    private String accountNumber;
    @NotBlank(message = "Name is mandatory")
    private String name;
    @NotNull
    private List<TransactionResponseDto> transactions;
    @Positive(message = "Balance must be positive")
    BigDecimal balance;
}
