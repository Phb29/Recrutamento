
export class UsuarioDto {
  constructor(
    public id?: any,
    public login?: string,
    public senha?: string,
    public nome?: string,
    public sexo?: string,
    public email?: string,
    public userType?: string | null | undefined
  ) {
    }
}
