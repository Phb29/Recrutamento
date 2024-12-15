export class EnderecoDTO{
    constructor(
        public endereco?: string,
        public rua?: string,
        public estado?: string,
        public cidade?: string,
        public cep?: string,
    ) {
    }
}