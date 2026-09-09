package com.example.EventSchedulingSystem.services;

import com.example.EventSchedulingSystem.Dtos.eventDtos.EventRequestDto;
import com.example.EventSchedulingSystem.Dtos.eventDtos.EventResponseDto;
import com.example.EventSchedulingSystem.models.Event;
import com.example.EventSchedulingSystem.models.User;
import com.example.EventSchedulingSystem.repositories.EventRepository;
import com.example.EventSchedulingSystem.repositories.UserRepository;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import java.util.List;


@Service
public class EventService {
    private final UserRepository userRepository;
    private final EventRepository eventRepository;
    public EventService(UserRepository userRepository,
                        EventRepository eventRepository){
        this.userRepository = userRepository;
        this.eventRepository = eventRepository;
    }

    public User getLoogedUser(){
        String email = SecurityContextHolder.getContext().getAuthentication().getName();
        return userRepository.findByEmail(email)
                .orElseThrow(() -> new IllegalArgumentException("User not found"));
    }

    public EventResponseDto convertToDto(Event event){
        return new EventResponseDto(
                event.getId(),
                event.getTitle(),
                event.getDescription(),
                event.getLocation(),
                event.getDateTime(),
                event.getStatus()
        );
    }

    public List<EventResponseDto> list(){
        User user = getLoogedUser();
        return eventRepository.findByUser(user)
                .stream()
                .map(this::convertToDto)
                .toList();
    }

    public EventResponseDto create(EventRequestDto dto){
        User user = getLoogedUser();
        Event event = new Event(
                dto.getTitle(),
                dto.getDescription(),
                dto.getLocation(),
                dto.getDateTime(),
                dto.getStatus()
        );
        eventRepository.save(event);
        user.getEventList().add(event);
        return convertToDto(event);
    }

    public EventResponseDto findById(Long id){
        User user = getLoogedUser();
        Event event = eventRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Event not found"));
        if (!user.getId().equals(event.getId())){
            throw new IllegalArgumentException("Acess denied");
        }
        return convertToDto(event);
    }

    public String remove(Long id){
        User user = getLoogedUser();
        Event event = eventRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Event not found"));
        if (!user.getId().equals(event.getId())){
            throw new IllegalArgumentException("Acess denied.");
        }
        eventRepository.delete(event);
        return "Event was removed successfully!";
    }

    public List<EventResponseDto> findByLocation(String location){
        User user = getLoogedUser();
        return eventRepository.findByUserAndLocation(user, location)
                .stream()
                .map(this::convertToDto)
                .toList();
    }

    public List<EventResponseDto> findByStatus(Event.EventStatus status){
        User user = getLoogedUser();
        return eventRepository.findByUserAndStatus(user, status)
                .stream()
                .map(this::convertToDto)
                .toList();
    }


}
