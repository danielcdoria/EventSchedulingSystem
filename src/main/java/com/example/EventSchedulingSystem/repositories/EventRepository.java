package com.example.EventSchedulingSystem.repositories;

import com.example.EventSchedulingSystem.models.Event;
import com.example.EventSchedulingSystem.models.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface EventRepository extends JpaRepository<Event, Long> {
    List<Event> findByUserList(User user);
    List<Event> findByUserListAndLocation(User user, String location);
    List<Event> findByUserListAndStatus(User user, Event.EventStatus status);
}
