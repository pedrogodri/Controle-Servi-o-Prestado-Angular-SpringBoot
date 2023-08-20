import { Component, OnInit } from '@angular/core';
import { Router, ActivatedRoute } from '@angular/router';
import { Cliente } from '../model/cliente';
import { ClientesService } from 'src/app/service/clientes.service';

@Component({
  selector: 'app-clientes-form',
  templateUrl: './clientes-form.component.html',
  styleUrls: ['./clientes-form.component.css']
})
export class ClientesFormComponent implements OnInit {
  cliente: Cliente;
  sucesso: boolean = false;
  errors?: String[];
  id: number = 0;

  constructor(
    private service: ClientesService,
    private router: Router,
    private activatedRoute: ActivatedRoute
  ) {
    this.cliente = new Cliente();
  }
  ngOnInit(): void {
    this.activatedRoute.params.subscribe(params => {
    this.id = params['id'];

      if (this.id) {
        this.service.getClienteById(this.id).subscribe(
          response => {
            this.cliente = response;
          },
          errorResponse => {
            this.cliente = new Cliente();
          }
        );
      }
    });
    console.log()
  }

  onSubmit(): void {
    if (this.id) {
      this.service.atualizar(this.cliente).subscribe(
        resposne => {
          this.sucesso = true;
          this.errors = [];
        },
        errorResponse => {
          this.errors = ['Erro ao atualizar o cliente'];
        }
      )
    } else {
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

  voltarListagem(): void {
    this.router.navigate(['/clientes/lista'])
  }
}
