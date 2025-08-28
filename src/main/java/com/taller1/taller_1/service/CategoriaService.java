package com.taller1.taller_1.service;

import com.taller1.taller_1.model.Categoria;
import com.taller1.taller_1.repository.CategoriaRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

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
}