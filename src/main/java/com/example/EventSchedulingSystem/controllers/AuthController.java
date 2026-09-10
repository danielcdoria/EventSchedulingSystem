package com.example.EventSchedulingSystem.controllers;

import com.example.EventSchedulingSystem.Dtos.authDtos.AuthResponseDto;
import com.example.EventSchedulingSystem.Dtos.authDtos.LoginRequestDto;
import com.example.EventSchedulingSystem.Dtos.authDtos.RegisterRequestDto;
import com.example.EventSchedulingSystem.services.AuthService;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class AuthController {
    private final AuthService service;
    public AuthController(AuthService service){
        this.service = service;
    }

    @PostMapping("/auth/register")
    public ResponseEntity<AuthResponseDto> register(@Valid @RequestBody RegisterRequestDto dto){
        return ResponseEntity.status(201).body(service.register(dto));
    }

    @PostMapping("/auth/login")
    public ResponseEntity<AuthResponseDto> login(@Valid @RequestBody LoginRequestDto dto){
        return ResponseEntity.status(200).body(service.login(dto));
    }
}
