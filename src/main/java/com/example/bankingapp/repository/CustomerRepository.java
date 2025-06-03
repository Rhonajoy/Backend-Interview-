package com.example.bankingapp.repository;

import com.example.bankingapp.entity.Customer;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface CustomerRepository extends JpaRepository<Customer,Long> {

    Optional<Customer> findByAccountNumber(String  accountNumber);
}
