package com.eventplanner.bookingservice.model;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "bookings")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Booking {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private Long eventId;

    private Long userId;

    private Integer ticketCount;

    private String status; // CREATED, CONFIRMED, CANCELLED
}
