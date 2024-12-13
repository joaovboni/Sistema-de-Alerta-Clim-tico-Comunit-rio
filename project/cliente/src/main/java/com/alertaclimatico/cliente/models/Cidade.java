package com.alertaclimatico.cliente.models;

import java.time.LocalDateTime;

public class Cidade {
    private Long id;
    private String nome;
    private String estado;
    private Double latitude;
    private Double longitude;
    private Integer populacao;
    private LocalDateTime dataCadastro;
    
    // Getters e Setters
    
    public Long getId() {
        return id;
    }
    
    public void setId(Long id) {
        this.id = id;
    }
    
    public String getNome() {
        return nome;
    }
    
    public void setNome(String nome) {
        this.nome = nome;
    }
    
    public String getEstado() {
        return estado;
    }
    
    public void setEstado(String estado) {
        this.estado = estado;
    }
    
    // ... demais getters e setters
}