package com.example.bankingapp.service;

import com.example.bankingapp.dto.CreateUser;
import com.example.bankingapp.dto.CustomerResponseDto;
import com.example.bankingapp.dto.UserResponseDto;
import com.example.bankingapp.entity.User;
import com.example.bankingapp.entity.UserRole;
import com.example.bankingapp.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class UserService {
    private final UserRepository userRepository;
    @Autowired
    private UserService(UserRepository userRepository){
        this.userRepository=userRepository;
    }

    public UserResponseDto createUser(CreateUser dto){
        User user=new User();
        user.setEmail(dto.getEmail());
        user.setRole(UserRole.valueOf(dto.getRole()));
        user.setPassword(dto.getPassword());
        User savedUser= userRepository.save(user);
        return toDto(savedUser);


    }
    private UserResponseDto toDto(User user ){
        UserResponseDto dto=new UserResponseDto();
        dto.setId(user.getId());
        dto.setPassword(user.getPassword());
        dto.setEmail(user.getEmail());
        List<CustomerResponseDto> customerDtos= new ArrayList<>();
        if (user.getCustomers() != null) {
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
