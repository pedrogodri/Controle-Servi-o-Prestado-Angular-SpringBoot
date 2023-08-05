package com.br.projeto.clientesback.Controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

import com.br.projeto.clientesback.model.Cliente;
import com.br.projeto.clientesback.repository.ClienteRepository;

@RestController
@RequestMapping("/api/clientes")
public class ClienteController {

    private final ClienteRepository repository;
    
    @Autowired
    public ClienteController(ClienteRepository repository) {
        this.repository = repository;
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public Cliente salvar(@RequestBody Cliente cliente) {
        return repository.save(cliente);
    }

    @GetMapping("{id}")
    public Cliente BuscarId(@PathVariable Integer id) {
        return repository.findById(id).orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND));
    }

    @DeleteMapping("{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deletar(@PathVariable Integer id) {
        repository
        .findById(id)
        .map(cliente -> {
            repository.delete(cliente);
            return Void.TYPE;
        })
        .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND));
    }

    @PutMapping("{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void editar(@PathVariable Integer id, @RequestBody Cliente clienteAtt) {
        repository
        .findById(id)
        .map(cliente -> {
            cliente.setNome(clienteAtt.getNome());
            cliente.setCpf(clienteAtt.getCpf());
            repository.save(clienteAtt);
            return Void.TYPE;
        })
        .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND));
    }
}
