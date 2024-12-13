package com.alertaclimatico.cliente.utils;

import javafx.fxml.FXMLLoader;
import javafx.scene.control.Dialog;
import javafx.scene.control.DialogPane;
import java.io.IOException;

public class DialogFactory {
    
    public static <T> Dialog<T> criarDialog(String fxmlPath, String titulo) {
        try {
            FXMLLoader loader = new FXMLLoader(DialogFactory.class.getResource(fxmlPath));
            DialogPane dialogPane = loader.load();
            
            Dialog<T> dialog = new Dialog<>();
            dialog.setDialogPane(dialogPane);
            dialog.setTitle(titulo);
            
            return dialog;
        } catch (IOException e) {
            throw new RuntimeException("Erro ao criar diálogo", e);
        }
    }
    
    public static void mostrarErro(String titulo, String mensagem) {
        javafx.scene.control.Alert alert = new javafx.scene.control.Alert(
            javafx.scene.control.Alert.AlertType.ERROR);
        alert.setTitle(titulo);
        alert.setHeaderText(null);
        alert.setContentText(mensagem);
        alert.showAndWait();
    }
    
    public static boolean confirmarAcao(String titulo, String mensagem) {
        javafx.scene.control.Alert alert = new javafx.scene.control.Alert(
            javafx.scene.control.Alert.AlertType.CONFIRMATION);
        alert.setTitle(titulo);
        alert.setHeaderText(null);
        alert.setContentText(mensagem);
        
        return alert.showAndWait()
            .filter(response -> response == javafx.scene.control.ButtonType.OK)
            .isPresent();
    }
}