package com.taller1.taller_1.listener;

import com.taller1.taller_1.repository.DenunciaRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class DesarchivamientoListener {

    private final DenunciaRepository denunciaRepository;

    @RabbitListener(queues = "denuncia.desarchivada.queue")
    public void onDesarchivamiento(Long denunciaId) {
        System.out.println("📬 Denuncia desarchivada recibida: " + denunciaId);

        denunciaRepository.findById(denunciaId).ifPresent(denuncia -> {
            denuncia.setArchivado(false);
            denunciaRepository.save(denuncia);
            System.out.println("✅ Denuncia " + denunciaId + " marcada como activa nuevamente.");
        });
    }
}

