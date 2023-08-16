package com.br.projeto.clientesback.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.br.projeto.clientesback.model.ServicoPrestado;

public interface ServicoPrestadoRepository extends JpaRepository<ServicoPrestado, Integer> {
    
}
