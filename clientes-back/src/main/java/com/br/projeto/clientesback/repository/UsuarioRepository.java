package com.br.projeto.clientesback.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.br.projeto.clientesback.model.Usuario;

public interface UsuarioRepository extends JpaRepository<Usuario, Integer> {
    
}
