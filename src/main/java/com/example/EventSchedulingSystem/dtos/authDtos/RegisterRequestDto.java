package com.example.EventSchedulingSystem.dtos.authDtos;

import com.example.EventSchedulingSystem.models.User;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public class RegisterRequestDto {
    @NotBlank(message = "Name cannot be blank")
    private String name;
    @Email(message = "Email must have @ and .com")
    private String email;
    @NotBlank(message = "Password cannot be blank")
    private String password;
    @NotNull(message = "Role cannot be null")
    private User.Role role;

    public RegisterRequestDto(String name,
                              String email,
                              String password,
                              User.Role role){

        this.name = name;
        this.email = email;
        this.password = password;
        this.role = role;
    }

    public User.Role getRole() {
        return role;
    }

    public String getPassword() {
        return password;
    }

    public String getEmail() {
        return email;
    }

    public String getName() {
        return name;
    }
}
