package com.eventplanner.EventPlanner.payment.model;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;

@Entity
@Table(name = "payments")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Payment {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private Long bookingId;

    private Double amount;

    private String status; // CREATED, PAID, FAILED

    private LocalDateTime paymentDate;
}
