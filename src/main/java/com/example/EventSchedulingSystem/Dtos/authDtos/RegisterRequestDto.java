package com.example.EventSchedulingSystem.Dtos.authDtos;

import com.example.EventSchedulingSystem.models.Event;
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
