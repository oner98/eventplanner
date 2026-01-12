package com.eventplanner.paymentservice.mapper;

import com.eventplanner.paymentservice.dto.PaymentRequestDTO;
import com.eventplanner.paymentservice.dto.PaymentResponseDTO;
import com.eventplanner.paymentservice.model.Payment;
import com.eventplanner.paymentservice.model.PaymentStatus;

import java.time.LocalDateTime;

public class PaymentMapper {

    // DTO → Entity
    public static Payment toEntity(PaymentRequestDTO dto) {
        return Payment.builder()
                .eventId(dto.getEventId())
                .amount(dto.getAmount())
                .status(PaymentStatus.PENDING)
                .paymentDate(LocalDateTime.now())
                .build();
    }

    // Entity → DTO
    public static PaymentResponseDTO toResponse(Payment payment) {
        return PaymentResponseDTO.builder()
                .id(payment.getId())
                .amount(payment.getAmount())
                .status(payment.getStatus().name())
                .build();
    }
}