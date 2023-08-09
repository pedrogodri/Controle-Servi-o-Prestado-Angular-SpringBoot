import { Injectable } from '@angular/core';
import { Cliente } from '../clientes/model/cliente';
import { HttpClient } from '@angular/common/http';
import { Observable } from 'rxjs';

@Injectable({
  providedIn: 'root'
})
export class ClientesService {

  constructor(private http: HttpClient) { }

  private url: string = 'http://localhost:8080/api/clientes';

  salvar(cliente: Cliente): Observable<Cliente> {
    return this.http.post<Cliente>(this.url, cliente);
  }

  getCliente(): Cliente {
    let cliente: Cliente = new Cliente();
    cliente.nome = 'Pedro Godri';
    cliente.cpf = '104.752.299-32';
    return cliente;
  }
}
