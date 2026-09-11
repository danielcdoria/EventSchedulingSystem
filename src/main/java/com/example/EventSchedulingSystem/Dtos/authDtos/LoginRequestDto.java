package com.example.EventSchedulingSystem.Dtos.authDtos;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;

public class LoginRequestDto {
    @Email(message = "Email must have @ and .com")
    private String email;
    @NotBlank(message = "Password cannot be blank")
    private String password;

    public String getPassword() {
        return password;
    }

    public String getEmail() {
        return email;
    }
}
