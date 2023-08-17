package com.br.projeto.clientesback.Controller;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

import com.br.projeto.clientesback.dto.ServicoPrestadoDTO;
import com.br.projeto.clientesback.model.Cliente;
import com.br.projeto.clientesback.model.ServicoPrestado;
import com.br.projeto.clientesback.repository.ClienteRepository;
import com.br.projeto.clientesback.repository.ServicoPrestadoRepository;
import com.br.projeto.clientesback.util.BigDecimalConverter;

import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("api/servicos-prestados")
@RequiredArgsConstructor
public class ServicoPrestadoController {


    private final ClienteRepository clienteRepository;
    private final ServicoPrestadoRepository repository;
    private final BigDecimalConverter BigDecimalconverter;

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public ServicoPrestado salvar(@RequestBody ServicoPrestadoDTO dto) {
        LocalDate data = LocalDate.parse(dto.getData(), DateTimeFormatter.ofPattern("dd/MM/yyyy"));
        Integer idCliente = dto.getIdCliente();
        Cliente cliente = 
            clienteRepository
                .findById(idCliente)
                .orElseThrow(() -> 
                    new ResponseStatusException(
                        HttpStatus.BAD_REQUEST, "Cliente inexistente"));
        ServicoPrestado servicoPrestado = new ServicoPrestado();
        servicoPrestado.setDescricao(dto.getDescricao());
        servicoPrestado.setData(data);
        servicoPrestado.setCliente(cliente);
        servicoPrestado.setValor( BigDecimalconverter.converter(dto.getPreco()) );
        return repository.save(servicoPrestado);
    }

    @GetMapping
    public List<ServicoPrestado> pesquisar(
        @RequestParam(value = "nome", required = false, defaultValue = "") String nome, 
        @RequestParam(value = "mes", required = false) Integer mes) {
        return repository.findByNomeClienteAndMes("%" + nome + "%", mes);
    }
}
