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
    public Jackson2JsonMessageConverter jackson2JsonMessageConverter() {
        return new Jackson2JsonMessageConverter();
    }
}
