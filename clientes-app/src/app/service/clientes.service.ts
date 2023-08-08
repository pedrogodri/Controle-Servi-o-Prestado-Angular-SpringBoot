import { Injectable } from '@angular/core';
import { Cliente } from '../clientes/model/cliente';

@Injectable({
  providedIn: 'root'
})
export class ClientesService {

  constructor() { }

  getCliente(): Cliente {
    let cliente: Cliente = new Cliente();
    cliente.nome = 'Pedro Godri';
    cliente.cpf = '104.752.299-32';
    return cliente;
  }
}
