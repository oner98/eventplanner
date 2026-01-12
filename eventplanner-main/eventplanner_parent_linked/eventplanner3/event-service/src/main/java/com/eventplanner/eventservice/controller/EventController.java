package com.eventplanner.eventservice.controller;

import com.eventplanner.eventservice.model.Event;
import com.eventplanner.eventservice.service.EventService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/events")
@RequiredArgsConstructor
public class EventController {

    private final EventService eventService;

    // ➕ CREATE EVENT
    @PostMapping
    public Event createEvent(@RequestBody Event event) {
        return eventService.createEvent(event);
    }

    // 📄 GET ALL EVENTS
    @GetMapping
    public List<Event> getAllEvents() {
        return eventService.getAllEvents();
    }

    // 🔍 GET EVENT BY ID
    @GetMapping("/{id}")
    public Event getEventById(@PathVariable Long id) {
        return eventService.getEventById(id);
    }
}
