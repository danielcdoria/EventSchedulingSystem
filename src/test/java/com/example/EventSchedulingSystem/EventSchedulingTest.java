package com.example.EventSchedulingSystem;

import com.example.EventSchedulingSystem.repositories.EventRepository;
import com.example.EventSchedulingSystem.repositories.UserRepository;
import com.example.EventSchedulingSystem.services.AuthService;
import com.example.EventSchedulingSystem.services.EventService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

@ExtendWith(MockitoExtension.class)
public class EventSchedulingTest {
    @Mock
    private UserRepository userRepository;

    @Mock
    private EventRepository eventRepository;

    @InjectMocks
    private EventService eventService;
}
