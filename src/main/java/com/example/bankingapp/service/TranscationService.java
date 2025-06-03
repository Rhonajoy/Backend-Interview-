package com.example.bankingapp.service;

import com.example.bankingapp.dto.TransactionDto;
import com.example.bankingapp.dto.TransactionResponseDto;
import com.example.bankingapp.entity.Customer;
import com.example.bankingapp.entity.Transaction;
import com.example.bankingapp.repository.CustomerRepository;
import com.example.bankingapp.repository.TransactionRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class TranscationService {
    public final TransactionRepository transactionRepository;
    public final CustomerRepository customerRepository;

    public TranscationService(TransactionRepository transactionRepository, CustomerRepository customerRepository) {
        this.transactionRepository = transactionRepository;
        this.customerRepository = customerRepository;
    }
    public List<TransactionResponseDto>getTransationByCustomerId(Long customerId){
        List<Transaction>transactions=transactionRepository.findByCustomerId(customerId);
        return transactions.stream().map(this::toDto).collect(Collectors.toList());

    }
    public Page<TransactionResponseDto> getAllTransactions(Pageable pageable){
        return transactionRepository.findAll(pageable)
                .map(this::toDto);
    }
    public TransactionResponseDto createTransaction(TransactionDto dto){
        Customer customer = customerRepository.findById(dto.getCustomerId()).orElseThrow(
                ()->new RuntimeException("Customer not found"));
        BigDecimal newBalance;

        switch (dto.getStatus()) {
            case PENDING -> {

                newBalance = customer.getBalance();
            }
            case SUCCESSFUL -> {
                if (dto.getAmount().compareTo(BigDecimal.ZERO) > 0) {
                    newBalance = customer.getBalance().add(dto.getAmount());
                } else {
                    BigDecimal withdrawalAmount = dto.getAmount().abs();
                    if (customer.getBalance().compareTo(withdrawalAmount) < 0) {
                        throw new RuntimeException("Insufficient balance");
                    }
                    newBalance = customer.getBalance().subtract(withdrawalAmount);
                }
            }
            case FAILED -> {

                newBalance = customer.getBalance();
            }
            default -> throw new RuntimeException("Unsupported transaction status");
        }
        customer.setBalance(newBalance);
        customerRepository.save(customer);
        Transaction tx=new Transaction();
        tx.setCustomer(customer);
        tx.setAmount(dto.getAmount());
        tx.setStatus(dto.getStatus());
        Transaction savedTx = transactionRepository.save(tx);

        return toDto(savedTx);


    }
    private TransactionResponseDto toDto(Transaction tx){
        TransactionResponseDto dto= new TransactionResponseDto();
        dto.setTransactionRef(tx.getTransactionRef());
        dto.setAmount(tx.getAmount());
        dto.setStatus(tx.getStatus());
        dto.setCustomerId(tx.getCustomer().getId());
        dto.setTransactionTime(tx.getTransactionTime());
        dto.setName(tx.getCustomer().getName());

        return dto;

    }
}
