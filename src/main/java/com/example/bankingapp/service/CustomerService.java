package com.example.bankingapp.service;

import com.example.bankingapp.dto.CustomerResponseDto;
import com.example.bankingapp.dto.CreateCustomer;
import com.example.bankingapp.dto.TransactionResponseDto;
import com.example.bankingapp.entity.Customer;
import com.example.bankingapp.entity.Transaction;
import com.example.bankingapp.repository.CustomerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.UUID;


@Service
public class CustomerService {
    private final CustomerRepository customerRepository;

    @Autowired
    public CustomerService(CustomerRepository customerRepository){
        this.customerRepository = customerRepository;
    }


    public CustomerResponseDto createCustomer(CreateCustomer dto){
        Customer customer =new Customer();
        customer.setName(dto.getName());
        customer.setAccountNumber(UUID.randomUUID().toString());
        customer.setEmail(dto.getEmail());
        customer.setBalance(dto.getBalance());
        Customer savedCustomer= customerRepository.save(customer);
        return toDto(savedCustomer);


    }

    public Page<CustomerResponseDto> getAllCustomers(Pageable pageable){
        return customerRepository.findAll(pageable).map(this::toDto);

    }
    public CustomerResponseDto getCustomerById(Long id){
        Customer customer = customerRepository.findById(id)
                .orElseThrow(() -> new NoSuchElementException("Customer not found with id " + id));
        customer.getTransactions().size();

        return toDto(customer);

    }

    private CustomerResponseDto toDto(Customer customer){
        CustomerResponseDto dto= new CustomerResponseDto();
        dto.setId(customer.getId());
        dto.setAccountNumber(customer.getAccountNumber());
        dto.setName(customer.getName());
        dto.setBalance(customer.getBalance());
        List<TransactionResponseDto> transactionDtos = new ArrayList<>();
        if (customer.getTransactions() != null) {
            for (Transaction transaction : customer.getTransactions()) {
                TransactionResponseDto txDto = new TransactionResponseDto();
                txDto.setTransactionRef(transaction.getTransactionRef());
                txDto.setStatus(transaction.getStatus());
                txDto.setAmount(transaction.getAmount());
                txDto.setCustomerId(customer.getId());
                txDto.setName(customer.getName());
                transactionDtos.add(txDto);
            }
        }
        dto.setTransactions(transactionDtos);
        return  dto;
    }
}
