package com.br.projeto.clientesback.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.br.projeto.clientesback.model.Servico;

public interface ServicoRepository extends JpaRepository<Servico, Integer> {
    
}
