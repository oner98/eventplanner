package com.eventplanner.eventservice.repository;

import com.eventplanner.eventservice.model.Event;
import org.springframework.data.jpa.repository.JpaRepository;

public interface EventRepository extends JpaRepository<Event, Long> {
}
