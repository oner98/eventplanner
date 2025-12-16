package com.eventplanner.EventPlanner.booking.controller;

import com.eventplanner.EventPlanner.booking.model.Booking;
import com.eventplanner.EventPlanner.booking.service.BookingService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/bookings")
@RequiredArgsConstructor
public class BookingController {

    private final BookingService bookingService;

    @PostMapping
    public ResponseEntity<Booking> createBooking(
            @RequestParam Long userId,
            @RequestParam Long eventId) {

        return ResponseEntity.ok(
                bookingService.createBooking(userId, eventId)
        );
    }
}
