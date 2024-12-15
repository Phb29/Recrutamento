export class CadastroVagaDto {
  constructor(
    public id?: any,
    public titulo?: string,
    public descricao?: string,
    public requisitos?: string,
    public idRecrutador?: number
  ) {}
}
