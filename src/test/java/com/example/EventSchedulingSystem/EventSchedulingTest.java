package com.example.EventSchedulingSystem;

import com.example.EventSchedulingSystem.dtos.eventDtos.EventResponseDto;
import com.example.EventSchedulingSystem.models.Event;
import com.example.EventSchedulingSystem.models.User;
import com.example.EventSchedulingSystem.repositories.EventRepository;
import com.example.EventSchedulingSystem.repositories.UserRepository;
import com.example.EventSchedulingSystem.services.EventService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.context.SecurityContextImpl;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class EventSchedulingTest {
    @Mock
    private UserRepository userRepository;

    @Mock
    private EventRepository eventRepository;

    @BeforeEach
    public void setup(){
        SecurityContextHolder.setContext(
                new SecurityContextImpl(
                        new UsernamePasswordAuthenticationToken("daniel@gmail.com", null, List.of())
                )
        );
    }

    @InjectMocks
    private EventService service;

    @Test
    public void findById_whenEventListHasEvent_search(){
        Event event = new Event("Run", "Every day", "Brasil", LocalDateTime.of(2026, 12, 15, 0, 0), Event.EventStatus.IN_PROGRESS);
        User user = new User();
        user.getEventList().add(event);
        when(userRepository.findByEmail("daniel@gmail.com")).thenReturn(Optional.of(user));
        when(eventRepository.findById(99L)).thenReturn(Optional.of(event));

        EventResponseDto result = service.findById(99L);

        assertThat(result.getTitle()).isEqualTo("Run");
    }

    @Test
    public void findById_whenEventListDoesNotHaveEvent_throwsException(){
        when(userRepository.findByEmail("daniel@gmail.com")).thenReturn(Optional.of(new User()));
        when(eventRepository.findById(99L)).thenReturn(Optional.of(new Event()));

        assertThatThrownBy(() -> service.findById(99L))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage("Acess denied.");
    }

    @Test
    public void remove_whenEventListHasEvent_remove(){
        User user = new User();
        Event event = new Event("Run", "Every day", "Brasil", LocalDateTime.of(2026, 12, 15, 0, 0), Event.EventStatus.IN_PROGRESS);
        user.getEventList().add(event);
        when(userRepository.findByEmail("daniel@gmail.com")).thenReturn(Optional.of(user));
        when(eventRepository.findById(1L)).thenReturn(Optional.of(event));

        String result = service.remove(1L);

        assertThat(result).isEqualTo("Event was removed successfully!");
    }

    @Test
    public void remove_whenEventListDoesNotHasEvent_throwsException(){
        when(userRepository.findByEmail("daniel@gmail.com")).thenReturn(Optional.of(new User()));
        when(eventRepository.findById(1L)).thenReturn(Optional.of(new Event()));

        assertThatThrownBy(() -> service.remove(1L))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage("Acess denied.");
    }
}
