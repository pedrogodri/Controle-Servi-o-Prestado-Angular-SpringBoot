import { ServicoPrestado } from './../servico-prestado/model/servico-prestado';
import { HttpClient, HttpParams } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';
import { ServicoPrestadoBusca } from '../servico-prestado/servico-prestado-lista/servicoPrestadoBusca';

@Injectable({
  providedIn: 'root'
})
export class ServicoPrestadoService {

  url: string = 'http://localhost:8080/api/servicos-prestados';

  constructor(private http: HttpClient) { }

  salvar(servicoPrestado: ServicoPrestado): Observable<ServicoPrestado> {
    return this.http.post<ServicoPrestado>(this.url, servicoPrestado);
  }

  buscar(nome:string, mes:number): Observable<ServicoPrestadoBusca[]> {
    const httpParams = new HttpParams().set("nome", nome).set("mes", mes ? mes.toString()  : '');
    console.log(`${this.url}?${httpParams.toString()}`)
    return this.http.get<any>(`${this.url}?${httpParams.toString()}`)
  }
}
