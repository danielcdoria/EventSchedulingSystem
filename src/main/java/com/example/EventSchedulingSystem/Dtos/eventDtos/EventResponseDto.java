package com.example.EventSchedulingSystem.Dtos.eventDtos;

import com.example.EventSchedulingSystem.models.Event;

import java.time.LocalDateTime;

public class EventResponseDto {
    private Long id;
    private String title;
    private String description;
    private String location;
    private LocalDateTime dateTime;
    private Event.EventStatus status;

    public EventResponseDto(Long id,
                            String title,
                            String description,
                            String location,
                            LocalDateTime dateTime,
                            Event.EventStatus status){
        this.id = id;
        this.title = title;
        this.description = description;
        this.location = location;
        this.dateTime = dateTime;
        this.status = status;
    }

    public String getDescription() {
        return description;
    }

    public LocalDateTime getDateTime() {
        return dateTime;
    }

    public Event.EventStatus getStatus() {
        return status;
    }

    public String getLocation() {
        return location;
    }

    public String getTitle() {
        return title;
    }

    public Long getId() {
        return id;
    }
}
