package com.hotel.Hotel.dto.response;

import com.fasterxml.jackson.annotation.JsonSubTypes;
import com.fasterxml.jackson.annotation.JsonTypeInfo;
import java.util.UUID;

@JsonTypeInfo(use = JsonTypeInfo.Id.NAME, include = JsonTypeInfo.As.EXISTING_PROPERTY, property = "tipo", visible = true)
@JsonSubTypes({
        @JsonSubTypes.Type(value = HabitacionEstandarResponse.class, name = "ESTANDAR"),
        @JsonSubTypes.Type(value = SuitePresidencialResponse.class, name = "SUITE")
})
public sealed interface HabitacionResponse
        permits HabitacionEstandarResponse, SuitePresidencialResponse {

    UUID id();

    String tipo();

    String numero();

    double precioPorNoche();

    int capacidadMaxima();

    String estado();
}
