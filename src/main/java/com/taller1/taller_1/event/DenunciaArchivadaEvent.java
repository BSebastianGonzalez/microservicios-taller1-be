package com.taller1.taller_1.event;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class DenunciaArchivadaEvent {
    private Long denunciaId;
    private Long adminId;
    private String justificacion;
    private String nombreAdmin;
    private LocalDateTime fechaArchivado;
}