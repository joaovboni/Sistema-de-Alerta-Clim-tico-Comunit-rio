package com.alertaclimatico.cliente.controllers;

import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import com.alertaclimatico.cliente.models.*;
import com.alertaclimatico.cliente.services.RabbitMQService;

public class MainController {
    
    @FXML private TableView<Cidade> tabelaCidades;
    @FXML private TableView<Alerta> tabelaAlertas;
    @FXML private TableView<Notificacao> tabelaNotificacoes;
    
    private RabbitMQService rabbitMQService;
    
    @FXML
    public void initialize() {
        rabbitMQService = new RabbitMQService();
        configurarTabelas();
        carregarDados();
    }
    
    private void configurarTabelas() {
        // Configuração das colunas e listeners
    }
    
    private void carregarDados() {
        // Carregar dados iniciais via RabbitMQ
    }
    
    @FXML
    public void novaCidade() {
        // Abrir dialog de nova cidade
    }
    
    @FXML
    public void editarCidade() {
        // Abrir dialog de edição
    }
    
    @FXML
    public void excluirCidade() {
        // Confirmar e excluir cidade
    }
    
    @FXML
    public void novoAlerta() {
        // Abrir dialog de novo alerta
    }
    
    @FXML
    public void atualizarStatusAlerta() {
        // Atualizar status do alerta
    }
    
    @FXML
    public void filtrarNotificacoes() {
        // Aplicar filtros nas notificações
    }
}