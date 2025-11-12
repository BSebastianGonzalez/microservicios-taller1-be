package com.taller1.taller_1.listener;

import com.taller1.taller_1.event.DenunciaArchivadaEvent;
import com.taller1.taller_1.event.DenunciaDesarchivadaEvent;
import com.taller1.taller_1.repository.DenunciaRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class DesarchivamientoListener {

    private final DenunciaRepository denunciaRepository;

    @RabbitListener(queues = "denuncia.desarchivada.queue")
    public void handleDenunciaDesarchivada(DenunciaDesarchivadaEvent event) {
        denunciaRepository.findById(event.getDenunciaId()).ifPresent(denuncia -> {
            denuncia.setArchivado(false);
            denunciaRepository.save(denuncia);
        });

        System.out.println("Denuncia desarchivada (ID: " + event.getDenunciaId());
    }
}

