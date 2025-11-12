package com.taller1.taller_1.repository;

import com.taller1.taller_1.model.Denuncia;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface DenunciaRepository extends JpaRepository<Denuncia,Long> {
    Optional<Denuncia> findByTokenSeguimiento(String token);
    List<Denuncia> findByArchivadoTrue();
}
