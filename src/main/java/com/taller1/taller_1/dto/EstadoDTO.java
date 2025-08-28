package com.taller1.taller_1.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.RequiredArgsConstructor;

import java.util.ArrayList;
import java.util.List;

@Data
@AllArgsConstructor
@RequiredArgsConstructor
public class EstadoDTO {
    private String nombre;
    private String descripcion;
    private List<Long> siguientes = new ArrayList<>();
}
