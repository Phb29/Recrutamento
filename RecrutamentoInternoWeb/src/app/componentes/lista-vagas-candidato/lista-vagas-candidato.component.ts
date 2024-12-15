import { Component, EventEmitter, Input, OnInit, Output } from '@angular/core';
import { AplicacaoDto } from '../../../classe/aplicacao.dto';
import { MessageService } from 'primeng/api';
import { ListaVagaService } from '../../service/lista-vaga.service';

@Component({
  selector: 'app-lista-vagas-candidato',
  templateUrl: './lista-vagas-candidato.component.html',
  styleUrls: ['./lista-vagas-candidato.component.scss']
})
export class ListaVagasCandidatoComponent implements OnInit {

  vagas: any[] = [];
  aplicacao: AplicacaoDto = new AplicacaoDto();
  notFound = false;

  @Output() editarVagaEvent = new EventEmitter<any>();
  @Input() idUsuario?: number;

  constructor(
    private listaVagaService: ListaVagaService,
    private messageService: MessageService,
  ) {}

  ngOnInit(): void {
    this.carregarVagas();
  }


  carregarVagas(): void {
    this.notFound = false;
    this.listaVagaService.getListaVagasCandidatoGeral().subscribe({
      next: (response: any) => {
        this.vagas = response.data || [];
        if (this.vagas.length === 0) {
          this.notFound = true;
        }
      },
      error: (err) => {
        console.error('Erro ao buscar vagas:', err);
      }
    });
  }

  sendApply(idVaga: number): void {
    this.aplicacao.idVaga = idVaga;
    this.aplicacao.idUsuario = this.idUsuario;

    this.listaVagaService.aplicarVaga(this.aplicacao).subscribe({
      next: (retorno: any) => {
        if (retorno.status === 200) {
          this.messageService.add({
            severity: 'success',
            summary: retorno.message,
            detail: retorno.description
          });
        } else if (retorno.status === 409) {
          this.messageService.add({
            severity: 'warn',
            summary: retorno.message,
            detail: retorno.description
          });
        } else {
          this.messageService.add({
            severity: 'error',
            summary: 'Erro ao registrar aplicação'
          });
        }
      },
      error: () => {
        this.messageService.add({
          severity: 'error',
          summary: 'Erro no servidor ao aplicar para a vaga'
        });
      }
    });
  }

  getIconClass(index: number): string {
    const classes = ['red-icon', 'blue-icon', 'green-icon', 'purple-icon'];
    return classes[index % classes.length];
  }
}
