package com.taller1.taller_1.service;

import com.taller1.taller_1.dto.EstadisticaCategoriaDTO;
import com.taller1.taller_1.model.Categoria;
import com.taller1.taller_1.repository.CategoriaRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class CategoriaService {
    @Autowired
    private final CategoriaRepository categoriaRepository;

    public List<Categoria> obtenerCategorias() {
        return categoriaRepository.findAll();
    }

    public Optional<Categoria> obtenerCategoriaPorId(Long id) {
        return categoriaRepository.findById(id);
    }

    public Categoria crearCategoria(Categoria categoria) {
        return categoriaRepository.save(categoria);
    }

    public void borrarCategoria(Long id) {
        categoriaRepository.deleteById(id);
    }

    public List<EstadisticaCategoriaDTO> obtenerEstadisticasPorCategoria() {
        List<Categoria> categorias = categoriaRepository.findAll();

        // 1️⃣ Calcular total de denuncias global sumando el campo totalDenuncias
        int totalDenunciasGlobal = categorias.stream()
                .mapToInt(Categoria::getTotalDenuncias)
                .sum();

        if (totalDenunciasGlobal == 0) totalDenunciasGlobal = 1; // evitar división por 0

        final int total = totalDenunciasGlobal; // ✅ variable final para usar dentro de lambda

        // 2️⃣ Mapear cada categoría con su porcentaje y total global
        return categorias.stream()
                .map(c -> {
                    double porcentaje = (c.getTotalDenuncias() * 100.0) / total;
                    return new EstadisticaCategoriaDTO(
                            c.getNombre(),
                            c.getTotalDenuncias(),
                            porcentaje,
                            total
                    );
                })
                .collect(Collectors.toList());
    }
}