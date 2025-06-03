package com.example.bankingapp.controller;


import com.example.bankingapp.dto.CustomerResponseDto;
import com.example.bankingapp.dto.CreateCustomer;
import com.example.bankingapp.service.CustomerService;
import jakarta.validation.Valid;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/customers")
public class CustomerController {

    private  final CustomerService customerService;

    public CustomerController(CustomerService customerService) {
        this.customerService = customerService;
    }

    @PostMapping
    public ResponseEntity<CustomerResponseDto>createCustomer(@Valid @RequestBody CreateCustomer dto){
        return  ResponseEntity.ok(customerService.createCustomer(dto));
    }
    @GetMapping
    public ResponseEntity<Page<CustomerResponseDto>> getAllCustomers(@RequestParam(defaultValue = "0") int page,
                                                                     @RequestParam(defaultValue = "10") int size){
        Pageable pageable = PageRequest.of(page, size);
        return  ResponseEntity.ok(customerService.getAllCustomers(pageable));
    }
    @GetMapping("/{id}")
    public  ResponseEntity<CustomerResponseDto>getCustomerById(@PathVariable Long id){
        return ResponseEntity.ok(customerService.getCustomerById(id));
    }

}
