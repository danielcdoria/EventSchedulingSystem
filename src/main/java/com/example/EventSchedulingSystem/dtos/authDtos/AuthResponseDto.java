package com.example.EventSchedulingSystem.dtos.authDtos;

public class AuthResponseDto {
    private String token;

    public AuthResponseDto(String token){
        this.token = token;
    }

    public String getToken() {
        return token;
    }
}
