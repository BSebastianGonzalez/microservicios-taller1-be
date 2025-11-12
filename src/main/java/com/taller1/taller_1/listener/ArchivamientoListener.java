package com.taller1.taller_1.listener;

import com.taller1.taller_1.event.DenunciaArchivadaEvent;
import com.taller1.taller_1.repository.DenunciaRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class ArchivamientoListener {

    private final DenunciaRepository denunciaRepository;

    @RabbitListener(queues = "denuncia.archivada.queue")
    public void handleDenunciaArchivada(DenunciaArchivadaEvent event) {
        denunciaRepository.findById(event.getDenunciaId()).ifPresent(denuncia -> {
            denuncia.setArchivado(true);
            denunciaRepository.save(denuncia);
        });

        System.out.println("Denuncia archivada (ID: " + event.getDenunciaId() + ") por admin " + event.getNombreAdmin());
    }
}
