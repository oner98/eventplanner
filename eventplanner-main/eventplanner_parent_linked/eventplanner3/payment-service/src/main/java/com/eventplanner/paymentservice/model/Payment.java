package com.eventplanner.paymentservice.model;

import jakarta.persistence.*;
import lombok.*;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Entity
@Table(name = "payments")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Payment {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    /**
     * Ödeme hangi event için yapıldı
     */
    @Column(nullable = false)
    private Long eventId;

    /**
     * Ödeme tutarı
     */
    @Column(nullable = false, precision = 10, scale = 2)
    private BigDecimal amount;

    /**
     * PAYMENT STATUS
     */
    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private PaymentStatus status;

    /**
     * Ödeme zamanı
     */
    @Column(nullable = false)
    private LocalDateTime paymentDate;
}
