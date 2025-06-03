package com.example.bankingapp.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import lombok.Data;

import java.math.BigDecimal;
import java.util.List;

@Entity
@Data
public class Customer {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private  Long id;
    @Column(unique = true,nullable = false)
    @NotBlank(message = "Account  Number is mandatory")
    private String accountNumber;
    @NotBlank(message = "Customer name  is mandatory")
    private String name;
    @NotBlank(message = "Customer email  is mandatory")
    private String email;
    @Column(name = "balance", nullable = false)
    private BigDecimal balance = BigDecimal.ZERO;
    @OneToMany(mappedBy = "customer", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private List<Transaction> transactions;

    @ManyToOne
    @JoinColumn(name = "user_id", nullable = false)
    private User user;
}
