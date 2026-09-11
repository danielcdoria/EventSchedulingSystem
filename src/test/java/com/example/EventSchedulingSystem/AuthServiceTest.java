package com.example.EventSchedulingSystem;

import com.example.EventSchedulingSystem.dtos.authDtos.AuthResponseDto;
import com.example.EventSchedulingSystem.dtos.authDtos.RegisterRequestDto;
import com.example.EventSchedulingSystem.models.User;
import com.example.EventSchedulingSystem.repositories.UserRepository;
import com.example.EventSchedulingSystem.security.JwtUtil;
import com.example.EventSchedulingSystem.services.AuthService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.security.crypto.password.PasswordEncoder;
import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.*;
import java.util.Optional;
import static org.mockito.ArgumentMatchers.any;

@ExtendWith(MockitoExtension.class)
public class AuthServiceTest {

    @Mock
    private UserRepository repository;

    @Mock
    private PasswordEncoder passwordEncoder;

    @Mock
    private JwtUtil jwtUtil;

    @InjectMocks
    private AuthService service;

    @Test
    public void register_whenEmailIsNew_savesAndReturnsToken(){
        RegisterRequestDto dto = new RegisterRequestDto("Daniel", "daniel@email.com", "senha123", User.Role.USER);
        when(repository.findByEmail("daniel@email.com")).thenReturn(Optional.empty());
        when(passwordEncoder.encode("senha123")).thenReturn("senhaEncoded");
        when(jwtUtil.generateToken("daniel@email.com")).thenReturn("token-fake");

        AuthResponseDto result = service.register(dto);

        assertThat(result.getToken()).isEqualTo("token-fake");
        verify(repository, times(1)).save(any(User.class));
    }

    @Test
    public void register_whenEmailIsNotNew_throwsException(){
        
    }
}
