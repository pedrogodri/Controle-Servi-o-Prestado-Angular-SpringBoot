package com.br.projeto.clientesback.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.br.projeto.clientesback.model.Cliente;

public interface ClienteRepository extends JpaRepository<Cliente, Integer> {
    
}
