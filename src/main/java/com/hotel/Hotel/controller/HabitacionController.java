package com.hotel.Hotel.controller;

import com.hotel.Hotel.dto.request.CrearHabitacionEstandarRequest;
import com.hotel.Hotel.dto.request.CrearSuitePresidencialRequest;
import com.hotel.Hotel.dto.response.HabitacionResponse;
import com.hotel.Hotel.service.HabitacionService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.util.UriComponentsBuilder;

import java.net.URI;
import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/api/habitaciones")
public class HabitacionController {

    private final HabitacionService habitacionService;

    public HabitacionController(HabitacionService habitacionService) {
        this.habitacionService = habitacionService;
    }

    @PostMapping("/estandar")
    public ResponseEntity<HabitacionResponse> crearEstandar(
            @RequestBody CrearHabitacionEstandarRequest request,
            UriComponentsBuilder uriBuilder) {
        HabitacionResponse creada = habitacionService.crearEstandar(request);
        URI uri = uriBuilder.path("/api/habitaciones/{id}").buildAndExpand(creada.id()).toUri();
        return ResponseEntity.created(uri).body(creada);
    }

    @PostMapping("/suites")
    public ResponseEntity<HabitacionResponse> crearSuite(
            @RequestBody CrearSuitePresidencialRequest request,
            UriComponentsBuilder uriBuilder) {
        HabitacionResponse creada = habitacionService.crearSuite(request);
        URI uri = uriBuilder.path("/api/habitaciones/{id}").buildAndExpand(creada.id()).toUri();
        return ResponseEntity.created(uri).body(creada);
    }

    @GetMapping
    public ResponseEntity<List<HabitacionResponse>> listar() {
        return ResponseEntity.ok(habitacionService.listarTodas());
    }

    @GetMapping("/{id}")
    public ResponseEntity<HabitacionResponse> buscarPorId(@PathVariable UUID id) {
        return ResponseEntity.ok(habitacionService.obtenerPorId(id));
    }
}
