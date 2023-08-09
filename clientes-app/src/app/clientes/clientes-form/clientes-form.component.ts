import { Component } from '@angular/core';
import { Cliente } from '../model/cliente';
import { ClientesService } from 'src/app/service/clientes.service';

@Component({
  selector: 'app-clientes-form',
  templateUrl: './clientes-form.component.html',
  styleUrls: ['./clientes-form.component.css']
})
export class ClientesFormComponent {
  cliente: Cliente;
  sucesso: boolean = false;
  errors?: String[];

  constructor(private service: ClientesService) {
    this.cliente = new Cliente();
  }

  onSubmit(): void {
    this.service.salvar(this.cliente).subscribe(
      response => {
        this.sucesso = true;
        this.errors = [];
        this.cliente = response;
      },
      errorResponse => {
        this.sucesso = false;
        this.errors = errorResponse.error.errors;
      }
    );
  }
}
