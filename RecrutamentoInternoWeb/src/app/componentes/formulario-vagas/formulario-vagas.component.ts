import { Component, EventEmitter, Input, OnInit, Output } from '@angular/core';
import { MessageService } from 'primeng/api';
import { CadastroVagaDto } from '../../../classe/cadastro-vaga.dto';
import { RecrutadorService } from '../../service/recrutador.service';
import { Location } from '@angular/common';

@Component({
  selector: 'app-formulario-vagas',
  templateUrl: './formulario-vagas.component.html',
  styleUrls: ['./formulario-vagas.component.scss']
})
export class FormularioVagasComponent implements OnInit {

  constructor(
    private recrutadorService: RecrutadorService,
    private location: Location
  ) {}

  cadastroVaga = new CadastroVagaDto();
  invalTitulo = false;
  invalDescricao = false;
  titlePage = 'Cadastrar vagas';

  @Input() novaVaga = true;
  @Input() vagaSelecionada: CadastroVagaDto = new CadastroVagaDto();
  @Output() showMessage = new EventEmitter<any>();

  ngOnInit(): void {
    this.location.path();
    if (!this.novaVaga) {
      this.cadastroVaga = Object.assign({}, this.vagaSelecionada);
      this.titlePage = 'Editar vaga';
    } else {
      this.titlePage = 'Cadastrar vaga';
      this.cadastroVaga = new CadastroVagaDto();
    }
    console.log('ID do Recrutador:', localStorage.getItem('idUsuario'));
  }

  sendForm() {
    this.invalDescricao = this.valid(this.cadastroVaga.descricao);
    this.invalTitulo = this.valid(this.cadastroVaga.titulo);

    if (this.invalDescricao || this.invalTitulo) {
      if (this.invalDescricao) {
        this.showMessage.emit({ severity: 'error', summary: 'A Descrição é obrigatória' });
      }
      if (this.invalTitulo) {
        this.showMessage.emit({ severity: 'error', summary: 'O Título é obrigatório' });
      }
      return;
    }
    console.log('ID do Recrutador:', localStorage.getItem('idUsuario'));
    // Recuperar o ID do recrutador do localStorage
    const idRecrutador = Number(localStorage.getItem('idUsuario'));
    if (!idRecrutador) {
      this.showMessage.emit({
        severity: 'error',
        summary: 'Erro ao identificar o recrutador. Faça login novamente.'
      });
      return;
    }

    // Chamar o serviço passando dois parâmetros
    this.recrutadorService.salvarVaga(this.cadastroVaga, idRecrutador).subscribe({
      next: (retorno: any) => {
        if (retorno.status === 200) {
          this.showMessage.emit({
            severity: 'success',
            summary: this.novaVaga ? 'Vaga criada com sucesso!' : 'Vaga editada com sucesso!'
          });
          this.resetarFormulario();
        } else {
          this.showMessage.emit({
            severity: 'error',
            summary: 'Erro ao salvar a vaga'
          });
        }
      },
      error: () => {
        this.showMessage.emit({
          severity: 'error',
          summary: 'Erro no servidor ao salvar a vaga'
        });
      }
    });
  }

  resetarFormulario() {
    this.cadastroVaga = new CadastroVagaDto();
    this.novaVaga = true;
    this.titlePage = 'Cadastrar vaga';
  }

  private valid(atributo: string | undefined) {
    return !atributo || atributo.length <= 1;
  }
}
