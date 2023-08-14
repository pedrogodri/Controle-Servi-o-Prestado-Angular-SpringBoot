import { Cliente } from './../clientes/model/cliente';
import { Injectable } from '@angular/core';
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

  atualizar(cliente: Cliente): Observable<any> {
    return this.http.put<Cliente>(`${this.url}/${cliente.id}`, cliente);
  }

  deletar(cliente:Cliente): Observable<any> {
    return this.http.delete<any>(`${this.url}/${cliente.id}`);
  }

  getClientes(): Observable<Cliente[]> {
    return this.http.get<Cliente[]>(this.url);
  }

  getClienteById(id: number):Observable<Cliente> {
    return this.http.get<Cliente>(`${this.url}/${id}`);
  }
}
