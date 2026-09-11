package com.example.EventSchedulingSystem.models;

import jakarta.persistence.*;

import java.time.LocalDateTime;
import java.util.List;

@Entity
public class Event {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String title;
    private String description;
    private String location;
    private LocalDateTime dateTime;
    private EventStatus status;

    @ManyToMany (mappedBy = "eventList")
    private List<User> userList;

    public enum EventStatus{
        UPCOMING, IN_PROGRESS, FINISHED, CANCELLED
    }

    public Event(){}

    public Event(String title,
                 String description,
                 String location,
                 LocalDateTime dateTime,
                 EventStatus status){
        this.title = title;
        this.description = description;
        this.location = location;
        this.dateTime = dateTime;
        this.status = status;
    }

    public String getDescription() {
        return description;
    }

    public Long getId() {
        return id;
    }

    public String getTitle() {
        return title;
    }

    public EventStatus getStatus() {
        return status;
    }

    public List<User> getUserList() {
        return userList;
    }

    public LocalDateTime getDateTime() {
        return dateTime;
    }

    public String getLocation() {
        return location;
    }
}
