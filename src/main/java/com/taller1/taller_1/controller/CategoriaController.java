package com.taller1.taller_1.controller;

import com.taller1.taller_1.dto.CategoriaDTO;
import com.taller1.taller_1.dto.EstadisticaCategoriaDTO;
import com.taller1.taller_1.model.Categoria;
import com.taller1.taller_1.service.CategoriaService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/categorias")
@RequiredArgsConstructor
public class CategoriaController {
    private final CategoriaService categoriaService;

    @GetMapping("/list")
    public List<Categoria> getAllCategorias() {
        return categoriaService.obtenerCategorias();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Categoria> getCategoriaById(@PathVariable Long id) {
        return categoriaService.obtenerCategoriaPorId(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @PostMapping
    public Categoria createCategoria(@RequestBody CategoriaDTO categoriaDTO) {

        Categoria categoria = new Categoria();
        categoria.setNombre(categoriaDTO.getTitulo());
        categoria.setDescripcion(categoriaDTO.getDescripcion());
        return categoriaService.crearCategoria(categoria);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteCategoria(@PathVariable Long id) {
        categoriaService.borrarCategoria(id);
        return ResponseEntity.noContent().build();
    }

    @GetMapping("/categorias")
    public List<EstadisticaCategoriaDTO> obtenerEstadisticas() {
        return categoriaService.obtenerEstadisticasPorCategoria();
    }
}
