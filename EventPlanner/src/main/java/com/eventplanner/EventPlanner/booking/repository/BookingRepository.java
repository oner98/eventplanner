package com.eventplanner.EventPlanner.booking.repository;

import com.eventplanner.EventPlanner.booking.model.Booking;
import org.springframework.data.jpa.repository.JpaRepository;

public interface BookingRepository extends JpaRepository<Booking, Long> {
}
