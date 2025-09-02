package com.taller1.taller_1.service;

import com.taller1.taller_1.model.Denuncia;
import com.taller1.taller_1.repository.DenunciaRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class DenunciaService {

    @Autowired
    private final DenunciaRepository denunciaRepository;

    public List<Denuncia> obtenerDenuncias() {
        return denunciaRepository.findAll();
    }

    public Optional<Denuncia> obtenerDenunciaPorId(Long id) {
        return denunciaRepository.findById(id);
    }

    public Denuncia crearDenuncia(Denuncia denuncia) {
        denuncia.setFechaCreacion(new Date());
        denuncia.setTokenSeguimiento(UUID.randomUUID().toString());
        return denunciaRepository.save(denuncia);
    }

    public void borrarDenuncia(Long id) {
        denunciaRepository.deleteById(id);
    }

    public Optional<Denuncia> obtenerDenunciaPorToken(String token) {
        return denunciaRepository.findByTokenSeguimiento(token);
    }
}