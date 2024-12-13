package com.alertaclimatico.servidor.listeners;

import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;
import com.alertaclimatico.servidor.services.CidadeService;
import com.alertaclimatico.servidor.models.Cidade;

@Component
public class CidadeListener {
    
    private final CidadeService cidadeService;
    
    public CidadeListener(CidadeService cidadeService) {
        this.cidadeService = cidadeService;
    }
    
    @RabbitListener(queues = "cidades.queue")
    public void processarMensagem(Cidade cidade) {
        cidadeService.processarCidade(cidade);
    }
}