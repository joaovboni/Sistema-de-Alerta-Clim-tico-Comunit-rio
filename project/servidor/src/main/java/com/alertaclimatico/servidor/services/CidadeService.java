package com.alertaclimatico.servidor.services;

import org.springframework.stereotype.Service;
import com.alertaclimatico.servidor.repositories.CidadeRepository;
import com.alertaclimatico.servidor.models.Cidade;
import org.springframework.amqp.rabbit.core.RabbitTemplate;

@Service
public class CidadeService {
    
    private final CidadeRepository cidadeRepository;
    private final RabbitTemplate rabbitTemplate;
    
    public CidadeService(CidadeRepository cidadeRepository, RabbitTemplate rabbitTemplate) {
        this.cidadeRepository = cidadeRepository;
        this.rabbitTemplate = rabbitTemplate;
    }
    
    public void processarCidade(Cidade cidade) {
        Cidade cidadeSalva = cidadeRepository.save(cidade);
        // Notificar clientes sobre a atualização
        rabbitTemplate.convertAndSend("alerta_climatico", "cidades.atualizado", cidadeSalva);
    }
}