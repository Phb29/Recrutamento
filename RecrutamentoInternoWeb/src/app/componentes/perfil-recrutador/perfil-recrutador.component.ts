// @ts-ignore

import { Component, OnInit, ChangeDetectorRef, NgZone } from '@angular/core';
import { ActivatedRoute, Router } from '@angular/router';
import { MessageService } from 'primeng/api';
import { PaginaRecrutradorDto } from '../../../classe/pagina-recrutrador.dto';
import { UsuarioDto } from '../../../classe/usuario.dto';
import { CadastroVagaDto } from '../../../classe/cadastro-vaga.dto';
import {RecrutadorService} from '../../service/recrutador.service';
import {Notificacao, NotificacaoService} from '../../service/notificacao.service';


@Component({
  selector: 'app-perfil-recrutador',
  templateUrl: './perfil-recrutador.component.html',
  styleUrls: ['./perfil-recrutador.component.scss']
})
export class PerfilRecrutadorComponent implements OnInit {
  mostrarPainelNotificacoes = false;
  usuario: UsuarioDto = new UsuarioDto();
  mostrarVisaoGeral = true;
  mostrarMeuPerfil = false;
  mostrarCadastrarVagas = false;
  mostrarListarVagas = false;
  mostrarCandidatos = false;
  novaVaga = true;
  notificacoes: Notificacao[] = [];

  vagaSelecionada: CadastroVagaDto = new CadastroVagaDto();
  login: string | null | undefined;

  constructor(
    private route: Router,
    private activatedRoute: ActivatedRoute,
    private messageService: MessageService,
    private recrutadorService: RecrutadorService,
    private notificacaoService: NotificacaoService,
    private cdr: ChangeDetectorRef,
  ) {
  }

  ngOnInit(): void {
    this.mostrarVisaoGeral = true;
    this.mostrarMeuPerfil = false;
    this.mostrarCadastrarVagas = false;
    this.mostrarListarVagas = false;
    this.mostrarCandidatos = false;

    if (!sessionStorage.getItem('reloaded')) {
      sessionStorage.setItem('reloaded', 'true');
      window.location.reload();
    } else {
      sessionStorage.removeItem('reloaded');
    }

    this.activatedRoute.paramMap.subscribe((params) => {
      this.login = params.get('login');
      if (this.login) {
        this.carregarDadosUsuario(this.login);
      }
    });

    this.cdr.detectChanges();
  }

  carregarDadosUsuario(login: string): void {
    this.recrutadorService.getUsuarioPorLogin(login).subscribe({
      next: (response: any) => {
        this.usuario = response.data || response;

        this.carregarNotificacoes(this.usuario.id);
        this.cdr.detectChanges();
      },
      error: (err) => {
        console.error('Erro na requisição:', err);
      },
    });
  }

  carregarNotificacoes(idUsuario: number): void {
    this.notificacaoService.getNotificacoes(idUsuario).subscribe({
      next: (data) => {
        this.notificacoes = data;
      },
      error: (err) => {
        console.error('Erro ao carregar notificações:', err);
      },
    });
  }


  sair() {
    localStorage.clear();
    this.route.navigate(['/login']);
  }

  mostrarPainel(painel: string) {
    this.mostrarVisaoGeral = false;
    this.mostrarMeuPerfil = false;
    this.mostrarCadastrarVagas = false;
    this.mostrarListarVagas = false;
    this.mostrarCandidatos = false;

    switch (painel) {
      case 'visaoGeral':
        this.mostrarVisaoGeral = true;
        break;
      case 'meuPerfil':
        this.mostrarMeuPerfil = true;
        break;
      case 'cadastrarVagas':
        this.mostrarCadastrarVagas = true;
        this.novaVaga = true;
        break;
      case 'listarVagas':
        this.mostrarListarVagas = true;
        break;
      case 'mostrarCandidatos':
        this.mostrarCandidatos = true;
        break;
    }
  }

  showMessage($event: any) {
    this.messageService.add({
      severity: $event.severity,
      summary: $event.summary,
      detail: $event.detail
    });
  }

  editarVaga(vagaSelecionada: CadastroVagaDto) {
    this.mostrarPainel('cadastrarVagas');
    this.novaVaga = false;
    this.vagaSelecionada = vagaSelecionada;
  }

  fecharNotificacoes(): void {
    this.mostrarPainelNotificacoes = false;
  }

  temNotificacaoNaoLida(): boolean {
    return this.notificacoes.length > 0;
  }


  abrirNotificacoes(): void {
    this.mostrarPainelNotificacoes = !this.mostrarPainelNotificacoes;
    if (this.mostrarPainelNotificacoes) {

    }
}
}
