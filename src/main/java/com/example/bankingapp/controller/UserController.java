package com.example.bankingapp.controller;

import com.example.bankingapp.dto.CreateCustomer;
import com.example.bankingapp.dto.CreateUser;
import com.example.bankingapp.dto.CustomerResponseDto;
import com.example.bankingapp.dto.UserResponseDto;
import com.example.bankingapp.service.CustomerService;
import com.example.bankingapp.service.UserService;
import jakarta.validation.Valid;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
@RestController
@RequestMapping("/api/users")
public class UserController {

    private  final UserService userService;

        public UserController(UserService userService) {
            this.userService=userService;

        }

        @PostMapping
        public ResponseEntity<UserResponseDto> createUser(@Valid @RequestBody CreateUser dto){
            return  ResponseEntity.ok(userService.createUser(dto));
        }
        @GetMapping
        public ResponseEntity<Page<UserResponseDto>> getAllUsers(@RequestParam(defaultValue = "0") int page,
                                                                         @RequestParam(defaultValue = "10") int size){
            Pageable pageable = PageRequest.of(page, size);
            return  ResponseEntity.ok(userService.getAllUsers(pageable));
        }
        @GetMapping("/{id}")
        public  ResponseEntity<UserResponseDto>getCustomerById(@PathVariable Long id){
            return ResponseEntity.ok(userService.getUserById(id));
        }
}
