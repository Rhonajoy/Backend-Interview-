package com.example.bankingapp.controller;

import com.example.bankingapp.dto.TransactionDto;
import com.example.bankingapp.dto.TransactionResponseDto;
import com.example.bankingapp.service.CustomerService;
import com.example.bankingapp.service.TranscationService;
import jakarta.validation.Valid;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/transactions")
public class TransactionController {
    private final CustomerService customerService;

    private final TranscationService transcationService;

    public TransactionController(CustomerService customerService, TranscationService transcationService) {
        this.customerService = customerService;
        this.transcationService = transcationService;
    }
   @GetMapping
    public ResponseEntity <Page<TransactionResponseDto>>getAllTransactions(@RequestParam(defaultValue = "0") int page,
                                                                           @RequestParam(defaultValue = "10") int size){
       Pageable pageable = PageRequest.of(page, size);
        return  ResponseEntity.ok(transcationService.getAllTransactions(pageable));

    }
    @GetMapping("/customer/{customerId}")
    public ResponseEntity <List<TransactionResponseDto>>getTransaction(@PathVariable Long customerId){
        return  ResponseEntity.ok(transcationService.getTransationByCustomerId(customerId));
    }
    @PostMapping
    public ResponseEntity<TransactionResponseDto>createTransaction(@Valid @RequestBody TransactionDto dto){
        return  ResponseEntity.ok(transcationService.createTransaction(dto));
    }
}
