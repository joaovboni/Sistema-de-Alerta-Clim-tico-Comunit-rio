package com.alertaclimatico.cliente.services;

import java.util.Arrays;
import java.util.List;

public class EstadosService {
    
    private static final List<String> ESTADOS = Arrays.asList(
        "AC", "AL", "AP", "AM", "BA", "CE", "DF", "ES", "GO",
        "MA", "MT", "MS", "MG", "PA", "PB", "PR", "PE", "PI",
        "RJ", "RN", "RS", "RO", "RR", "SC", "SP", "SE", "TO"
    );
    
    public static List<String> getEstados() {
        return ESTADOS;
    }
    
    public static boolean isEstadoValido(String estado) {
        return ESTADOS.contains(estado.toUpperCase());
    }
}