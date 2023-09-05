package com.br.projeto.clientesback.model;

import java.time.LocalDate;

import org.hibernate.validator.constraints.br.CPF;

import com.fasterxml.jackson.annotation.JsonFormat;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.PrePersist;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Getter
@Setter
public class Cliente {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    
    @Column(nullable = false, length = 150)
    @NotEmpty(message = "{campo.nome.obrigatorio}")
    private String nome;
    
    @Column(nullable = false, length = 11)
    @NotNull(message = "{campo.cpf.obrigatorio}")
    @CPF(message = "{campo.cpf.invalido}")
    private String cpf;
    
    @Column(name = "data_cadastro", updatable = false)
    @JsonFormat(pattern = "dd/MM/yyy")
    private LocalDate dataCadastro;

    @PrePersist
    public void prePersist() {
        setDataCadastro(LocalDate.now());
    }
}
