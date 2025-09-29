package com.taller1.taller_1.repository;

import com.taller1.taller_1.model.ArchivoDenuncia;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ArchivoDenunciaRepository extends JpaRepository<ArchivoDenuncia, Long> {
    List<ArchivoDenuncia> findByDenunciaId(Long denunciaId);
}
