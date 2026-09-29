package com.hotel.Hotel.mapper;

import com.hotel.Hotel.domain.Cliente;
import com.hotel.Hotel.domain.Reserva;
import com.hotel.Hotel.dto.request.ActualizarClienteRequest;
import com.hotel.Hotel.dto.request.CrearClienteRequest;
import com.hotel.Hotel.dto.response.ClienteResponse;
import com.hotel.Hotel.dto.response.ClienteResumenResponse;
import com.hotel.Hotel.dto.response.ReservaItemResponse;
import org.mapstruct.BeanMapping;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;
import org.mapstruct.Named;
import org.mapstruct.NullValuePropertyMappingStrategy;

import java.util.List;

@Mapper(componentModel = "spring")
public interface ClienteMapper {

    ClienteResponse toResponse(Cliente cliente);

    List<ClienteResponse> toResponseList(List<Cliente> clientes);

    @Mapping(target = "id", ignore = true)
    @Mapping(target = "activo", ignore = true)
    @Mapping(target = "penalizaciones", ignore = true)
    @Mapping(target = "reservas", ignore = true)
    Cliente toEntity(CrearClienteRequest request);

    @Mapping(target = "totalReservasRealizadas", source = ".", qualifiedByName = "totalReservas")
    @Mapping(target = "montoTotalGastado", source = ".", qualifiedByName = "montoTotal")
    @Mapping(target = "reservasRecientes", source = "reservas")
    ClienteResumenResponse toResumen(Cliente cliente);

    @Mapping(target = "idReserva", source = "id")
    @Mapping(target = "numeroHabitacion", source = "habitacion.numero")
    @Mapping(target = "fechaInicio", source = "periodo.fechaInicio")
    @Mapping(target = "fechaFin", source = "periodo.fechaFin")
    @Mapping(target = "estado", expression = "java(reserva.getEstado().name())")
    ReservaItemResponse toReservaItem(Reserva reserva);

    List<ReservaItemResponse> toReservaItemList(List<Reserva> reservas);

    @Named("totalReservas")
    default int totalReservas(Cliente cliente) {
        if (cliente.getReservas() == null) {
            return 0;
        }
        return cliente.getReservas().size();
    }

    @Named("montoTotal")
    default double montoTotal(Cliente cliente) {
        if (cliente.getReservas() == null) {
            return 0.0;
        }
        return cliente.getReservas().stream()
                .mapToDouble(Reserva::getCostoTotal)
                .sum();
    }

    @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
    @Mapping(target = "id", ignore = true)
    @Mapping(target = "activo", ignore = true)
    @Mapping(target = "penalizaciones", ignore = true)
    @Mapping(target = "reservas", ignore = true)
    void updateClienteFromDto(ActualizarClienteRequest dto, @MappingTarget Cliente entity);
}
