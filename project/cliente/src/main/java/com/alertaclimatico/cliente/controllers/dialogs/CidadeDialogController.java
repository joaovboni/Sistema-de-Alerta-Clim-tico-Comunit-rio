package com.alertaclimatico.cliente.controllers.dialogs;

import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.collections.FXCollections;
import com.alertaclimatico.cliente.models.Cidade;
import com.alertaclimatico.cliente.utils.ValidadorCampos;
import com.alertaclimatico.cliente.services.EstadosService;

public class CidadeDialogController {
    
    @FXML private TextField campoNome;
    @FXML private ComboBox<String> comboEstado;
    @FXML private TextField campoLatitude;
    @FXML private TextField campoLongitude;
    @FXML private TextField campoPopulacao;
    @FXML private Label mensagemErro;
    
    private Cidade cidade;
    private boolean modoEdicao;
    
    @FXML
    public void initialize() {
        configurarCampos();
        configurarValidacoes();
    }
    
    private void configurarCampos() {
        comboEstado.setItems(FXCollections.observableArrayList(EstadosService.getEstados()));
        
        // Configurar formatação e validação dos campos numéricos
        campoLatitude.textProperty().addListener((obs, oldValue, newValue) -> {
            if (!newValue.matches("-?\\d*\\.?\\d*")) {
                campoLatitude.setText(oldValue);
            }
        });
        
        campoLongitude.textProperty().addListener((obs, oldValue, newValue) -> {
            if (!newValue.matches("-?\\d*\\.?\\d*")) {
                campoLongitude.setText(oldValue);
            }
        });
        
        campoPopulacao.textProperty().addListener((obs, oldValue, newValue) -> {
            if (!newValue.matches("\\d*")) {
                campoPopulacao.setText(newValue.replaceAll("[^\\d]", ""));
            }
        });
    }
    
    private void configurarValidacoes() {
        campoNome.focusedProperty().addListener((obs, oldValue, newValue) -> {
            if (!newValue) { // Quando o campo perde o foco
                validarCampoNome();
            }
        });
    }
    
    private boolean validarCampoNome() {
        String nome = campoNome.getText().trim();
        if (nome.isEmpty()) {
            mostrarErro("O nome da cidade é obrigatório");
            return false;
        }
        if (nome.length() < 3) {
            mostrarErro("O nome da cidade deve ter pelo menos 3 caracteres");
            return false;
        }
        ocultarErro();
        return true;
    }
    
    public void setCidade(Cidade cidade) {
        this.cidade = cidade;
        this.modoEdicao = cidade != null;
        
        if (modoEdicao) {
            preencherCampos();
        }
    }
    
    private void preencherCampos() {
        campoNome.setText(cidade.getNome());
        comboEstado.setValue(cidade.getEstado());
        campoLatitude.setText(String.valueOf(cidade.getLatitude()));
        campoLongitude.setText(String.valueOf(cidade.getLongitude()));
        campoPopulacao.setText(String.valueOf(cidade.getPopulacao()));
    }
    
    public Cidade getCidadeAtualizada() {
        if (!validarTodosCampos()) {
            return null;
        }
        
        Cidade cidadeAtualizada = modoEdicao ? cidade : new Cidade();
        cidadeAtualizada.setNome(campoNome.getText().trim());
        cidadeAtualizada.setEstado(comboEstado.getValue());
        cidadeAtualizada.setLatitude(Double.parseDouble(campoLatitude.getText()));
        cidadeAtualizada.setLongitude(Double.parseDouble(campoLongitude.getText()));
        cidadeAtualizada.setPopulacao(Integer.parseInt(campoPopulacao.getText()));
        
        return cidadeAtualizada;
    }
    
    private boolean validarTodosCampos() {
        if (!validarCampoNome()) return false;
        if (comboEstado.getValue() == null) {
            mostrarErro("Selecione um estado");
            return false;
        }
        if (!ValidadorCampos.validarLatitude(campoLatitude.getText())) {
            mostrarErro("Latitude inválida");
            return false;
        }
        if (!ValidadorCampos.validarLongitude(campoLongitude.getText())) {
            mostrarErro("Longitude inválida");
            return false;
        }
        if (campoPopulacao.getText().isEmpty()) {
            mostrarErro("Informe a população");
            return false;
        }
        return true;
    }
    
    private void mostrarErro(String mensagem) {
        mensagemErro.setText(mensagem);
        mensagemErro.setVisible(true);
    }
    
    private void ocultarErro() {
        mensagemErro.setVisible(false);
    }
}