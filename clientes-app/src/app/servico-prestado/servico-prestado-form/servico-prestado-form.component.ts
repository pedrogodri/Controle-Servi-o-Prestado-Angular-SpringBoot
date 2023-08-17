import { Component, OnInit } from '@angular/core';
import { Cliente } from '../../clientes/model/cliente';
import { ClientesService } from '../../service/clientes.service';
import { ServicoPrestado } from '../model/servico-prestado';
import { ServicoPrestadoService } from 'src/app/service/servico-prestado.service';

@Component({
  selector: 'app-servico-prestado-form',
  templateUrl: './servico-prestado-form.component.html',
  styleUrls: ['./servico-prestado-form.component.css']
})
export class ServicoPrestadoFormComponent implements OnInit {

  clientes: Cliente[] = [];
  servico: ServicoPrestado;

  constructor(
    private clienteService: ClientesService,
    private service: ServicoPrestadoService
  ) {
    this.servico = new ServicoPrestado();
  }

  ngOnInit(): void {
    this.clienteService.getClientes().subscribe(
      response => {
        this.clientes = response;
      }
    )
  }

  onSubmit() {
    this.service.salvar(this.servico).subscribe(
      response => {
        console.log(response);
      }

    )
  }

}
