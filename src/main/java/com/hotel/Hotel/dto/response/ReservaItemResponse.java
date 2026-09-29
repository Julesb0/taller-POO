package com.hotel.Hotel.dto.response;

import java.time.LocalDateTime;
import java.util.UUID;

public record ReservaItemResponse(
        UUID idReserva,
        String numeroHabitacion,
        LocalDateTime fechaInicio,
        LocalDateTime fechaFin,
        String estado,
        double costoTotal) {
}
