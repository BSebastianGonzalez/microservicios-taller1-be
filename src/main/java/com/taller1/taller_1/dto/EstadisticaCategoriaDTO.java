package com.taller1.taller_1.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class EstadisticaCategoriaDTO {
    private String nombreCategoria;
    private int totalDenuncias;
    private double porcentaje;
    private int totalDenunciasGlobal;
}
