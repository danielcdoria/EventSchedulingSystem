package com.example.EventSchedulingSystem.services;

import com.example.EventSchedulingSystem.dtos.authDtos.AuthResponseDto;
import com.example.EventSchedulingSystem.dtos.authDtos.LoginRequestDto;
import com.example.EventSchedulingSystem.dtos.authDtos.RegisterRequestDto;
import com.example.EventSchedulingSystem.models.User;
import com.example.EventSchedulingSystem.repositories.UserRepository;
import com.example.EventSchedulingSystem.security.JwtUtil;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class AuthService {
    private final JwtUtil jwtUtil;
    private final PasswordEncoder passwordEncoder;
    private final UserRepository repository;
    public AuthService(JwtUtil jwtUtil,
                       PasswordEncoder passwordEncoder,
                       UserRepository repository){
        this.jwtUtil = jwtUtil;
        this.passwordEncoder = passwordEncoder;
        this.repository = repository;
    }

    public AuthResponseDto register(RegisterRequestDto dto){
        if (repository.findByEmail(dto.getEmail()).isPresent()){
            throw new IllegalArgumentException("Email already being used");
        }
        String encodedPassword = passwordEncoder.encode(dto.getPassword());
        User user = new User(
                dto.getName(),
                dto.getEmail(),
                encodedPassword,
                dto.getRole()
        );
        repository.save(user);
        String token = jwtUtil.generateToken(user.getEmail());
        return new AuthResponseDto(token);
    }

    public AuthResponseDto login(LoginRequestDto dto){
        User user = repository.findByEmail(dto.getEmail())
                .orElseThrow(() -> new IllegalArgumentException("User not found"));
        if (!passwordEncoder.matches(dto.getPassword(), user.getPassword())){
            throw new IllegalArgumentException("Password or Email incorrect.");
        }
        if (!repository.findByEmail(dto.getEmail()).isPresent()){
            throw new IllegalArgumentException("User not found");
        }
        String token = jwtUtil.generateToken(user.getEmail());
        return new AuthResponseDto(token);
    }
}
