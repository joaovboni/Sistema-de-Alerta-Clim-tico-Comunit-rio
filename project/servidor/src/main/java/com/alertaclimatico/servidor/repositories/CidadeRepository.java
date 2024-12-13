package com.alertaclimatico.servidor.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import com.alertaclimatico.servidor.models.Cidade;

public interface CidadeRepository extends JpaRepository<Cidade, Long> {
    // Métodos personalizados de consulta
}