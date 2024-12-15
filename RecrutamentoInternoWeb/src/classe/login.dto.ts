
export class LoginDto {
  constructor(
    public id?: any,
    public login?: string,
    public senha?: string,
    public nome?: string,
    public email?: string,
    public sexo?: string,
    public tipoUsuario?: string | null
  ) {
    this.sexo = 'M';
    }
}
