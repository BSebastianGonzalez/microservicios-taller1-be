package com.taller1.taller_1.dto;


import com.taller1.taller_1.model.Estado;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.RequiredArgsConstructor;

@Data
@AllArgsConstructor
@RequiredArgsConstructor
public class ConsultaDenunciaDTO {
    private Long id;
    private String titulo;
    private Estado estado;
}
