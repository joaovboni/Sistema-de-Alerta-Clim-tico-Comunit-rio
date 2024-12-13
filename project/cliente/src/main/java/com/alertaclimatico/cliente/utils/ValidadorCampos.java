package com.alertaclimatico.cliente.utils;

public class ValidadorCampos {
    
    public static boolean validarLatitude(String latitude) {
        try {
            double valor = Double.parseDouble(latitude);
            return valor >= -90 && valor <= 90;
        } catch (NumberFormatException e) {
            return false;
        }
    }
    
    public static boolean validarLongitude(String longitude) {
        try {
            double valor = Double.parseDouble(longitude);
            return valor >= -180 && valor <= 180;
        } catch (NumberFormatException e) {
            return false;
        }
    }
    
    public static boolean validarPopulacao(String populacao) {
        try {
            int valor = Integer.parseInt(populacao);
            return valor > 0;
        } catch (NumberFormatException e) {
            return false;
        }
    }
    
    public static boolean validarCampoObrigatorio(String valor) {
        return valor != null && !valor.trim().isEmpty();
    }
}