package com.example.EventSchedulingSystem.repositories;

import com.example.EventSchedulingSystem.models.Event;
import com.example.EventSchedulingSystem.models.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface EventRepository extends JpaRepository<Event, Long> {
    List<Event> findByUser(User user);
    List<Event> findByUserAndLocation(User user, String location);
    List<Event> findByUserAndStatus(User user, Event.EventStatus status);
}
