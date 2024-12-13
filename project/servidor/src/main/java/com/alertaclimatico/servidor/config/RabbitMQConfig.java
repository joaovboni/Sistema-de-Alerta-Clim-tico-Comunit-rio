package com.alertaclimatico.servidor.config;

import org.springframework.amqp.core.*;
import org.springframework.amqp.rabbit.connection.ConnectionFactory;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.amqp.support.converter.Jackson2JsonMessageConverter;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class RabbitMQConfig {
    
    public static final String EXCHANGE_NAME = "alerta_climatico";
    
    @Bean
    public TopicExchange exchange() {
        return new TopicExchange(EXCHANGE_NAME);
    }
    
    @Bean
    public Queue cidadesQueue() {
        return new Queue("cidades.queue");
    }
    
    @Bean
    public Queue alertasQueue() {
        return new Queue("alertas.queue");
    }
    
    @Bean
    public Queue notificacoesQueue() {
        return new Queue("notificacoes.queue");
    }
    
    @Bean
    public Binding cidadesBinding(Queue cidadesQueue, TopicExchange exchange) {
        return BindingBuilder.bind(cidadesQueue).to(exchange).with("cidades.*");
    }
    
    @Bean
    public Binding alertasBinding(Queue alertasQueue, TopicExchange exchange) {
        return BindingBuilder.bind(alertasQueue).to(exchange).with("alertas.*");
    }
    
    @Bean
    public Binding notificacoesBinding(Queue notificacoesQueue, TopicExchange exchange) {
        return BindingBuilder.bind(notificacoesQueue).to(exchange).with("notificacoes.*");
    }
    
    @Bean
    public Jackson2JsonMessageConverter messageConverter() {
        return new Jackson2JsonMessageConverter();
    }
    
    @Bean
    public RabbitTemplate rabbitTemplate(ConnectionFactory connectionFactory) {
        RabbitTemplate template = new RabbitTemplate(connectionFactory);
        template.setMessageConverter(messageConverter());
        return template;
    }
}