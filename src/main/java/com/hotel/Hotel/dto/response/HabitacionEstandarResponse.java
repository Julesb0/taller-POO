package com.hotel.Hotel.dto.response;

import java.util.UUID;

public record HabitacionEstandarResponse(
        UUID id,
        String tipo,
        String numero,
        double precioPorNoche,
        int capacidadMaxima,
        String estado,
        int camasIndividuales) implements HabitacionResponse {
}
