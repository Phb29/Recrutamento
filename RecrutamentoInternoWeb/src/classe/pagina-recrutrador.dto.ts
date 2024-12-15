import {UsuarioDto} from './usuario.dto';


export class PaginaRecrutradorDto {
  constructor(
    public id?: any,
    public usuario?: UsuarioDto | null ,
  ) {
    this.usuario = usuario || new UsuarioDto();
    }
}
