package com.taller1.taller_1.controller;

import com.taller1.taller_1.model.ArchivoDenuncia;
import com.taller1.taller_1.service.ArchivoDenunciaService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

@RestController
@RequestMapping("/api/evidencia")
@RequiredArgsConstructor
public class ArchivoDenunciaController {
    @Autowired
    private final ArchivoDenunciaService archivoService;

    @GetMapping("/todos")
    public ResponseEntity<?> obtenerTodosLosArchivos() {
        List<ArchivoDenuncia> archivos = archivoService.obtenerTodosLosArchivos();
        return ResponseEntity.ok(archivos);
    }

    @PostMapping
    public ResponseEntity<?> uploadFile(@RequestParam("file") MultipartFile file,
                                        @RequestParam("denunciaId") Long denunciaId) {
        try {
            ArchivoDenuncia ad = archivoService.uploadFile(file, denunciaId);
            return ResponseEntity.ok(ad);
        } catch (IllegalArgumentException e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        } catch (Exception e) {
            return ResponseEntity.status(500).body("Error al subir archivo: " + e.getMessage());
        }
    }

    @GetMapping("/{denunciaId}")
    public ResponseEntity<?> obtenerArchivos(@PathVariable Long denunciaId) {
        List<ArchivoDenuncia> archivos = archivoService.obtenerArchivosPorDenuncia(denunciaId);
        return ResponseEntity.ok(archivos);
    }
}