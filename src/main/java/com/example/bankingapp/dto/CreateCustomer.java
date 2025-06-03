package com.example.bankingapp.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Positive;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;
@Getter
@Setter
@Data
public class CreateCustomer {
    @NotBlank(message = "Name is required")
    private String name;
    @NotBlank(message = "Customer email  is mandatory")
    private String email;
    @Positive(message = "Initial balance must be positive")
    BigDecimal balance;
}
