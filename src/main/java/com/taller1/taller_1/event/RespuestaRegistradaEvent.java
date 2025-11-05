package com.taller1.taller_1.event;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.Date;

@Data
@AllArgsConstructor
public class RespuestaRegistradaEvent {
    private Long denunciaId;
    private Date fechaRespuesta;
    private Long estado;
    private Integer appealDays;
    private Long respuestaId;
}
