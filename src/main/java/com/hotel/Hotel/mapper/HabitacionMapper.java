package com.hotel.Hotel.mapper;

import com.hotel.Hotel.domain.Habitacion;
import com.hotel.Hotel.domain.HabitacionEstandar;
import com.hotel.Hotel.domain.SuitePresidencial;
import com.hotel.Hotel.dto.response.HabitacionEstandarResponse;
import com.hotel.Hotel.dto.response.HabitacionResponse;
import com.hotel.Hotel.dto.response.SuitePresidencialResponse;
import org.hibernate.Hibernate;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import java.util.List;

@Mapper(componentModel = "spring")
public interface HabitacionMapper {

    default HabitacionResponse toResponse(Habitacion habitacion) {
        if (habitacion == null) {
            return null;
        }
        Habitacion concreta = (Habitacion) Hibernate.unproxy(habitacion);
        if (concreta instanceof HabitacionEstandar estandar) {
            return toEstandarResponse(estandar);
        }
        if (concreta instanceof SuitePresidencial suite) {
            return toSuiteResponse(suite);
        }
        throw new IllegalArgumentException("Tipo de habitación no soportado: " + concreta.getClass().getName());
    }

    default List<HabitacionResponse> toResponseList(List<Habitacion> habitaciones) {
        if (habitaciones == null) {
            return List.of();
        }
        return habitaciones.stream().map(this::toResponse).toList();
    }

    @Mapping(target = "tipo", constant = "ESTANDAR")
    @Mapping(target = "estado", expression = "java(habitacion.getEstado().name())")
    HabitacionEstandarResponse toEstandarResponse(HabitacionEstandar habitacion);

    @Mapping(target = "tipo", constant = "SUITE")
    @Mapping(target = "estado", expression = "java(habitacion.getEstado().name())")
    SuitePresidencialResponse toSuiteResponse(SuitePresidencial habitacion);
}
