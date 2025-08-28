package com.taller1.taller_1.service;

import com.taller1.taller_1.model.Estado;
import com.taller1.taller_1.repository.EstadoRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class EstadoService {

    private final EstadoRepository estadoRepository;

    public List<Estado> obtenerEstados() {
        return estadoRepository.findAll();
    }

    public Optional<Estado> obtenerEstadoPorId(Long id) {
        return estadoRepository.findById(id);
    }

    public Estado crearEstado(Estado estado) {
        return estadoRepository.save(estado);
    }

    public void borrarEstado(Long id) {
        estadoRepository.deleteById(id);
    }
}