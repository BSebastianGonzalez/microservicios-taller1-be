package com.taller1.taller_1.controller;

import com.taller1.taller_1.dto.EstadoDTO;
import com.taller1.taller_1.model.Estado;
import com.taller1.taller_1.service.EstadoService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/api/estados")
@RequiredArgsConstructor
public class EstadoController {
    private final EstadoService estadoService;

    @GetMapping("/list")
    public List<Estado> getAllEstados() {
        return estadoService.obtenerEstados();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Estado> getEstadoById(@PathVariable Long id) {
        return estadoService.obtenerEstadoPorId(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @PostMapping
    public Estado createEstado(@RequestBody EstadoDTO estadoDTO) {
        Estado nuevoEstado = new Estado(
                null,
                estadoDTO.getNombre(),
                estadoDTO.getDescripcion(),
                new ArrayList<>(),
                estadoDTO.getSiguientes()
        );

        return estadoService.crearEstado(nuevoEstado);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteEstado(@PathVariable Long id) {
        estadoService.borrarEstado(id);
        return ResponseEntity.noContent().build();
    }
}
