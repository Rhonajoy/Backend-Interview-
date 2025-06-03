package com.example.bankingapp.dto;

import jakarta.validation.constraints.NotBlank;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class CreateUser {
    @NotBlank(message = "Name is required")
    private String name;
    @NotBlank(message = "User email  is mandatory")
    private String email;
    @NotBlank(message = "User password  is mandatory")
    private String password;
    @NotBlank(message = "Role is required")
    private String Role;

}
