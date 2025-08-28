package com.taller1.taller_1.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class DenunciaDTO {
    private String titulo;
    private String descripcion;
    private List<Long> categoriasIds;
}
