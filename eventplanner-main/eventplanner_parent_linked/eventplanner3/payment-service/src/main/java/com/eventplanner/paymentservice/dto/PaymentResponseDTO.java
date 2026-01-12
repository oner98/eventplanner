package com.eventplanner.paymentservice.dto;

import lombok.*;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class PaymentResponseDTO {

    private Long id;
    private Long eventId;
    private BigDecimal amount;
    private String status;
    private LocalDateTime paymentDate;
}
