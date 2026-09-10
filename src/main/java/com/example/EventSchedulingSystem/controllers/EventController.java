package com.example.EventSchedulingSystem.controllers;

import com.example.EventSchedulingSystem.Dtos.eventDtos.EventRequestDto;
import com.example.EventSchedulingSystem.Dtos.eventDtos.EventResponseDto;
import com.example.EventSchedulingSystem.models.Event;
import com.example.EventSchedulingSystem.services.EventService;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class EventController {
    private final EventService service;
    public EventController(EventService service){
        this.service = service;
    }

    @GetMapping("/events")
    public ResponseEntity<List<EventResponseDto>> list(){
        return ResponseEntity.ok(service.list());
    }

    @PostMapping("/events")
    public ResponseEntity<EventResponseDto> create(@Valid @RequestBody EventRequestDto dto){
        return ResponseEntity.status(201).body(service.create(dto));
    }

    @GetMapping("/events/{id}")
    public ResponseEntity<EventResponseDto> findById(@PathVariable Long id){
        return ResponseEntity.ok(service.findById(id));
    }

    @DeleteMapping("/events/{id}")
    public ResponseEntity<String> remove(@PathVariable Long id){
        return ResponseEntity.ok(service.remove(id));
    }

    @GetMapping("/events/location")
    public ResponseEntity<List<EventResponseDto>> findByLocation(@RequestParam String location){
        return ResponseEntity.ok(service.findByLocation(location));
    }

    @GetMapping("/events/status")
    public ResponseEntity<List<EventResponseDto>> findByStatus(@RequestParam Event.EventStatus status){
        return ResponseEntity.ok(service.findByStatus(status));
    }
}
