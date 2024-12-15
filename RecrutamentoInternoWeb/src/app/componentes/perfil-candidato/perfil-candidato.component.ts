import { Component, OnInit } from '@angular/core';
import { CadastroVagaDto } from '../../../classe/cadastro-vaga.dto';
import { PaginaRecrutradorDto } from '../../../classe/pagina-recrutrador.dto';
import { UsuarioDto } from '../../../classe/usuario.dto';
import { ActivatedRoute, Router } from '@angular/router';
import { MessageService } from 'primeng/api';
import {PerfilService} from '../../service/perfil.service';


@Component({
  selector: 'app-perfil-candidato',
  templateUrl: './perfil-candidato.component.html',
  styleUrls: ['./perfil-candidato.component.scss'],
})
export class PerfilCandidatoComponent implements OnInit {
  usuario: UsuarioDto = new UsuarioDto();
  mostrarVisaoGeral = true;
  mostrarMeuPerfil = false;
  mostrarVagasAplicadas = false;
  mostrarCadastrarVagas = false;
  mostrarListarVagas = false;
  novaVaga = true;
  vagaSelecionada: CadastroVagaDto = new CadastroVagaDto();
  private login: string | null | undefined;

  constructor(
    private route: Router,
    private activatedRoute: ActivatedRoute,
    private perfilService: PerfilService
  ) {}

  ngOnInit(): void {
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
  }

  carregarDadosUsuario(login: string): void {
    this.perfilService.getPerfilUsuario(login).subscribe({
      next: (resposta) => {
        this.usuario = resposta.data;
      },
      error: (err) => {
        console.error('Erro ao buscar os dados do usuário:', err);
      },
    });
  }

  sair(): void {
    localStorage.clear();
    this.route.navigate(['/login']);
  }

  mostrarPainel(painel: string): void {
    this.mostrarVisaoGeral = false;
    this.mostrarMeuPerfil = false;
    this.mostrarCadastrarVagas = false;
    this.mostrarListarVagas = false;
    this.mostrarVagasAplicadas = false;

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
      case 'mostrarVagasAplicadas':
        this.mostrarVagasAplicadas = true;
        break;
      case 'listarVagas':
        this.mostrarListarVagas = true;
        break;
    }
  }

  editarVaga(vagaSelecionada: CadastroVagaDto): void {
    this.mostrarPainel('cadastrarVagas');
    this.novaVaga = false;
    this.vagaSelecionada = vagaSelecionada;
  }

}
