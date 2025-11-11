package com.taller1.taller_1.listener;

import com.taller1.taller_1.event.RespuestaRegistradaEvent;
import com.taller1.taller_1.model.Denuncia;
import com.taller1.taller_1.model.Estado;
import com.taller1.taller_1.repository.DenunciaRepository;
import com.taller1.taller_1.repository.EstadoRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class RespuestaListener {
    private final DenunciaRepository denunciaRepository;
    private final EstadoRepository estadoRepository;

    @RabbitListener(queues = "respuesta.queue")
    public void onRespuestaRegistrada(RespuestaRegistradaEvent event) {
        Long denunciaId = event.getDenunciaId();

        System.out.println("📩 Evento recibido para denuncia ID: " + denunciaId);

        Optional<Denuncia> opt = denunciaRepository.findById(denunciaId);
        if (opt.isPresent()) {
            Denuncia denuncia = opt.get();

            Estado estadoRespondida = estadoRepository.findById(5L)
                    .orElseThrow(() -> new RuntimeException("Estado con ID 5 no encontrado"));

            denuncia.setEstado(estadoRespondida);

            denunciaRepository.save(denuncia);

            System.out.println("✅ Denuncia ID " + denunciaId + " actualizada al estado RESPONDIDA (ID=5)");
        } else {
            System.err.println("⚠️ Denuncia con ID " + denunciaId + " no encontrada, no se actualizó.");
        }
    }
}

