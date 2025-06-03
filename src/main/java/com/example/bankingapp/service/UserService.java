package com.example.bankingapp.service;

import com.example.bankingapp.dto.CreateUser;
import com.example.bankingapp.dto.CustomerResponseDto;

import com.example.bankingapp.dto.UserResponseDto;
import com.example.bankingapp.entity.Customer;
import com.example.bankingapp.entity.User;
import com.example.bankingapp.entity.UserRole;
import com.example.bankingapp.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.NoSuchElementException;

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

    public Page<UserResponseDto> getAllUsers(Pageable pageable){
        return userRepository.findAll(pageable).map(this::toDto);

    }
    public UserResponseDto getUserById(Long id){
        User user= userRepository.findById(id)
                .orElseThrow(() -> new NoSuchElementException("User  not found with id " + id));


        return toDto(user);

    }
    private UserResponseDto toDto(User user ){
        UserResponseDto dto=new UserResponseDto();
        dto.setId(user.getId());
        dto.setPassword(user.getPassword());
        dto.setEmail(user.getEmail());
        List<CustomerResponseDto> customerDtos= new ArrayList<>();
        if (user.getCustomers() != null) {
            for (Customer customer : user.getCustomers()) {
                CustomerResponseDto customerResponseDto = new CustomerResponseDto();

                customerResponseDto.setAccountNumber(customer.getAccountNumber());
                customerResponseDto.setBalance(customer.getBalance());


            }
        }
       return  dto;

    }
}
