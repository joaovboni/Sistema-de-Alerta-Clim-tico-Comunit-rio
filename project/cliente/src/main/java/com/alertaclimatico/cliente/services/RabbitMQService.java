package com.alertaclimatico.cliente.services;

import com.rabbitmq.client.*;
import com.fasterxml.jackson.databind.ObjectMapper;
import java.io.IOException;
import java.util.concurrent.TimeoutException;

public class RabbitMQService {
    private static final String EXCHANGE_NAME = "alerta_climatico";
    private final Connection connection;
    private final Channel channel;
    private final ObjectMapper objectMapper;
    
    public RabbitMQService() {
        try {
            ConnectionFactory factory = new ConnectionFactory();
            factory.setHost("localhost");
            connection = factory.newConnection();
            channel = connection.createChannel();
            channel.exchangeDeclare(EXCHANGE_NAME, "topic");
            objectMapper = new ObjectMapper();
        } catch (IOException | TimeoutException e) {
            throw new RuntimeException("Erro ao inicializar RabbitMQ", e);
        }
    }
    
    public void enviarMensagem(String routingKey, Object mensagem) {
        try {
            String mensagemJson = objectMapper.writeValueAsString(mensagem);
            channel.basicPublish(EXCHANGE_NAME, routingKey, null, mensagemJson.getBytes());
        } catch (IOException e) {
            throw new RuntimeException("Erro ao enviar mensagem", e);
        }
    }
    
    public void registrarConsumidor(String queueName, String routingKey, DeliverCallback callback) {
        try {
            String queue = channel.queueDeclare(queueName, true, false, false, null).getQueue();
            channel.queueBind(queue, EXCHANGE_NAME, routingKey);
            channel.basicConsume(queue, true, callback, consumerTag -> {});
        } catch (IOException e) {
            throw new RuntimeException("Erro ao registrar consumidor", e);
        }
    }
    
    public void close() {
        try {
            channel.close();
            connection.close();
        } catch (IOException | TimeoutException e) {
            throw new RuntimeException("Erro ao fechar conexão", e);
        }
    }
}