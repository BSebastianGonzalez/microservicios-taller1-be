package com.taller1.taller_1.controller;

import com.taller1.taller_1.dto.ConsultaDenunciaDTO;
import com.taller1.taller_1.dto.DenunciaDTO;
import com.taller1.taller_1.model.Categoria;
import com.taller1.taller_1.model.Denuncia;
import com.taller1.taller_1.model.Estado;
import com.taller1.taller_1.repository.CategoriaRepository;
import com.taller1.taller_1.service.DenunciaService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/denuncias")
@RequiredArgsConstructor
public class DenunciaController {
    private final DenunciaService denunciaService;
    private final CategoriaRepository categoriaRepository;

    @GetMapping("/list")
    public List<Denuncia> obtenerDenuncias() {
        return denunciaService.obtenerDenuncias();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Denuncia> obtenerDenunciaPorId(@PathVariable Long id) {
        return denunciaService.obtenerDenunciaPorId(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @PostMapping
    public ResponseEntity<Map<String, String>> crearDenuncia(@RequestBody DenunciaDTO dto) {
        List<Categoria> categorias = categoriaRepository.findAllById(dto.getCategoriasIds());

        Denuncia denuncia = new Denuncia();
        denuncia.setTitulo(dto.getTitulo());
        denuncia.setDescripcion(dto.getDescripcion());
        denuncia.setCategorias(categorias);
        denuncia.setFechaCreacion(new java.util.Date());

        Estado estado = new Estado();
        estado.setId(1L);
        denuncia.setEstado(estado);

        Denuncia savedDenuncia = denunciaService.crearDenuncia(denuncia);

        Map<String, String> response = new HashMap<>();
        response.put("token", savedDenuncia.getTokenSeguimiento());
        response.put("id", String.valueOf(savedDenuncia.getId()));

        return ResponseEntity.status(HttpStatus.CREATED).body(response);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> borrarDenuncia(@PathVariable Long id) {
        denunciaService.borrarDenuncia(id);
        return ResponseEntity.noContent().build();
    }

    @GetMapping("/token/{token}")
    public ResponseEntity<ConsultaDenunciaDTO> obtenerDenunciaPorToken(@PathVariable String token) {
        return denunciaService.obtenerDenunciaPorToken(token)
                .map(denuncia -> {
                    ConsultaDenunciaDTO dto = new ConsultaDenunciaDTO(
                            denuncia.getId(),
                            denuncia.getTitulo(),
                            denuncia.getEstado()
                    );
                    return ResponseEntity.ok(dto);
                })
                .orElse(ResponseEntity.notFound().build());
    }
}
