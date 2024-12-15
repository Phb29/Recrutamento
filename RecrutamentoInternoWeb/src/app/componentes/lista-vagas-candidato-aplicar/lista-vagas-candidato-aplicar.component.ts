import { Component, EventEmitter, Input, OnInit, Output } from '@angular/core';
import { Router } from '@angular/router';
import { ListaVagaService } from '../../service/lista-vaga.service';
import { MessageService } from 'primeng/api';

@Component({
  selector: 'app-lista-vagas-candidato-aplicar',
  templateUrl: './lista-vagas-candidato-aplicar.component.html',
  styleUrls: ['./lista-vagas-candidato-aplicar.component.scss']
})
export class ListaVagasCandidatoAplicarComponent implements OnInit {

  vagas: any[] = [];
  notFound = false;

  @Output() editarVagaEvent = new EventEmitter<any>();
  @Input() idUsuario?: number;

  constructor(
    private listaVagaService: ListaVagaService
  ) {}

  ngOnInit(): void {
    this.carregarVagas();
  }

  carregarVagas(): void {
    this.notFound = false;
    this.listaVagaService.getListaVagasCandidato().subscribe({
      next: (response: any) => {
        this.vagas = response.data || [];
        if (this.vagas.length === 0) {
          this.notFound = true;
        }
      },
      error: (err) => {
        console.error('Erro ao buscar candidato Registrado:', err);
      }
    });
  }
}
