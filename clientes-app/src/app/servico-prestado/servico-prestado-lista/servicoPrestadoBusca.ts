import { Cliente } from "../../clientes/model/cliente";

export class ServicoPrestadoBusca {
  descricao?: string;
  valor?: number;
  data?: string;
  cliente: Cliente = new Cliente();
}
