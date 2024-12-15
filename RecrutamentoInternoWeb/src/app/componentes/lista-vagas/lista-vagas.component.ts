import { Component, OnInit, Output, EventEmitter } from '@angular/core';
import { CadastroVagaDto } from '../../../classe/cadastro-vaga.dto';
import { ListaVagaService } from '../../service/lista-vaga.service';
import {MessageService} from 'primeng/api';
import {RecrutadorService} from '../../service/recrutador.service';
import {MatSnackBar} from '@angular/material';

@Component({
  selector: 'app-lista-vagas',
  templateUrl: './lista-vagas.component.html',
  styleUrls: ['./lista-vagas.component.scss']
})
export class ListaVagasComponent implements OnInit {

  vagas: any[] = [];
  notFound = false;

  @Output() editarVagaEvent = new EventEmitter<any>();

  constructor(
    private listaVagaService: ListaVagaService,
    private messageService: MessageService,
    private recrutadorService: RecrutadorService,
    private snackBar: MatSnackBar
  ) {}

  ngOnInit(): void {
    this.carregarVagas();
  }

  carregarVagas(): void {
    this.notFound = false;
    this.listaVagaService.getListaVagas().subscribe({
      next: (response: any) => {
        this.vagas = response.data || [];
        if (this.vagas.length === 0) {
          this.notFound = true;
        }
      },
      error: (err) => {
        console.error('Erro ao listar vagas:', err);
      }
    });
  }
  editarVaga(vaga: CadastroVagaDto): void {
    this.editarVagaEvent.emit(vaga);
  }
  excluirVaga(idVaga: number): void {
    this.snackBar.open('Tem certeza que deseja excluir esta vaga?', 'Excluir', {
      duration: 5000, // 5 segundos
      panelClass: ['snack-confirm'],
      horizontalPosition: 'center',
      verticalPosition: 'top',
    }).onAction().subscribe(() => {
      this.recrutadorService.excluirVaga(idVaga).subscribe({
        next: () => {
          this.vagas = this.vagas.filter(vaga => vaga.id !== idVaga);
          this.snackBar.open('Vaga excluída com sucesso!', 'Fechar', {
            duration: 3000,
            panelClass: ['snack-success']
          });
        },
        error: (err) => {
          console.error('Erro ao excluir vaga:', err);
          this.snackBar.open('Erro ao excluir a vaga. Tente novamente!', 'Fechar', {
            duration: 3000,
            panelClass: ['snack-error']
          });
        }
      });
    });
  }
}
