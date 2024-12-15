import { Injectable } from '@angular/core';
import { HttpClient, HttpHeaders } from '@angular/common/http';
import { Observable } from 'rxjs';
import {environment} from '../../environments/environment';
import {AplicacaoDto} from '../../classe/aplicacao.dto';


@Injectable({
  providedIn: 'root',
})
export class ListaVagaService {
  private apiUrl = environment.apiUrl;

  constructor(private http: HttpClient) {
  }

  getListaVagas(): Observable<any> {
    const token = localStorage.getItem('token');
    const headers = new HttpHeaders({
      Authorization: `Bearer ${token}`,
    });

    return this.http.get<any>(`${this.apiUrl}/vaga/list`, {headers});
  }

  getListaVagasCandidato(): Observable<any> {
    const token = localStorage.getItem('token');
    const headers = new HttpHeaders({
      Authorization: `Bearer ${token}`,
    });
    return this.http.get<any>(`${this.apiUrl}/apply/list`, { headers });
  }

  getListaVagasCandidatoGeral(): Observable<any> {
    const token = localStorage.getItem('token');
    const headers = new HttpHeaders({ Authorization: `Bearer ${token}` });
    return this.http.get<any>(`${this.apiUrl}/vaga/list`, { headers });
  }

  aplicarVaga(aplicacao: AplicacaoDto): Observable<any> {
    const token = localStorage.getItem('token');
    const headers = new HttpHeaders({ Authorization: `Bearer ${token}` });
    return this.http.post<any>(`${this.apiUrl}/apply/save`, aplicacao, { headers });
  }

}
