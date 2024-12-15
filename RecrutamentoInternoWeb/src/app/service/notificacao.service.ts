import { Injectable } from '@angular/core';
import {HttpClient, HttpHeaders} from '@angular/common/http';
import { Observable } from 'rxjs';

export interface Notificacao {
  id: number;
  mensagem: string;
  dataCriacao: string;
  visualizada: boolean;
}

@Injectable({
  providedIn: 'root'
})
export class NotificacaoService {
  private baseUrl = 'http://localhost:8080/notificacoes';

  constructor(private http: HttpClient) {}

  getNotificacoes(idUsuario: number): Observable<Notificacao[]> {

    const token = localStorage.getItem('token');
    const headers = new HttpHeaders({
      Authorization: `Bearer ${token}`
    });


    return this.http.get<Notificacao[]>(`${this.baseUrl}/${idUsuario}`, { headers });
  }
}
