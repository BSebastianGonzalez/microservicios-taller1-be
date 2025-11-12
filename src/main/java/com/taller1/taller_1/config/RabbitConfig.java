package com.taller1.taller_1.config;

import org.springframework.amqp.core.Queue;
import org.springframework.amqp.support.converter.Jackson2JsonMessageConverter;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.amqp.core.Binding;
import org.springframework.amqp.core.BindingBuilder;
import org.springframework.amqp.core.TopicExchange;
import org.springframework.amqp.rabbit.annotation.EnableRabbit;


@Configuration
@EnableRabbit
public class RabbitConfig {

    public static final String EXCHANGE = "respuesta.exchange";
    public static final String ROUTING_KEY = "respuesta.creada";
    public static final String RESPUESTA_QUEUE = "respuesta.queue";

    public static final String ARCHIVAMIENTO_QUEUE = "denuncia.archivada.queue";
    public static final String ARCHIVAMIENTO_EXCHANGE = "denuncia.archivada.exchange";
    public static final String ARCHIVAMIENTO_ROUTING_KEY = "denuncia.archivada.key";

    public static final String DESARCHIVAMIENTO_QUEUE = "denuncia.desarchivada.queue";
    public static final String DESARCHIVAMIENTO_ROUTING_KEY = "denuncia.desarchivada";

    @Bean
    public Queue respuestaQueue() {
        return new Queue(RESPUESTA_QUEUE, true);
    }

    @Bean
    public TopicExchange exchange() {
        return new TopicExchange(EXCHANGE);
    }

    @Bean
    public Binding binding(Queue respuestaQueue, TopicExchange exchange) {
        return BindingBuilder.bind(respuestaQueue).to(exchange).with(ROUTING_KEY);
    }

    @Bean
    public Queue archivamientoQueue() {
        return new Queue(ARCHIVAMIENTO_QUEUE, true);
    }

    @Bean
    public TopicExchange archivamientoExchange() {
        return new TopicExchange(ARCHIVAMIENTO_EXCHANGE);
    }

    @Bean
    public Binding archivamientoBinding(Queue archivamientoQueue, TopicExchange archivamientoExchange) {
        return BindingBuilder.bind(archivamientoQueue)
                .to(archivamientoExchange)
                .with(ARCHIVAMIENTO_ROUTING_KEY);
    }

    @Bean
    public Jackson2JsonMessageConverter jackson2JsonMessageConverter() {
        return new Jackson2JsonMessageConverter();
    }

    @Bean
    public Queue desarchivamientoQueue() {
        return new Queue(DESARCHIVAMIENTO_QUEUE, true);
    }

    @Bean
    public Binding desarchivamientoBinding(Queue desarchivamientoQueue, TopicExchange archivamientoExchange) {
        return BindingBuilder.bind(desarchivamientoQueue)
                .to(archivamientoExchange)
                .with(DESARCHIVAMIENTO_ROUTING_KEY);
    }
}
