package com.example.EventSchedulingSystem.Dtos.eventDtos;

import com.example.EventSchedulingSystem.models.Event;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

import java.time.LocalDateTime;

public class EventRequestDto {
    @NotBlank(message = "Title cannot be blank")
    private String title;
    @NotBlank(message = "Description cannot be blank")
    private String description;
    @NotBlank(message = "Location cannot be blank")
    private String location;
    @NotNull(message = "Date time cannot be null")
    private LocalDateTime dateTime;
    @NotNull(message = "Status cannot be null")
    private Event.EventStatus status;

    public String getLocation() {
        return location;
    }

    public Event.EventStatus getStatus() {
        return status;
    }

    public LocalDateTime getDateTime() {
        return dateTime;
    }

    public String getDescription() {
        return description;
    }

    public String getTitle() {
        return title;
    }


}
