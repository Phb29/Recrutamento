import { Injectable } from '@angular/core';
import { HttpClient, HttpHeaders } from '@angular/common/http';
import { Observable } from 'rxjs';
import {environment} from '../../environments/environment';
import {CadastroVagaDto} from '../../classe/cadastro-vaga.dto';



@Injectable({
  providedIn: 'root',
})
export class RecrutadorService {
  private apiUrl = environment.apiUrl;

  constructor(private http: HttpClient) {
  }

  getUsuarioPorLogin(login: string): Observable<any> {
    const token = localStorage.getItem('token');
    const headers = new HttpHeaders({
      Authorization: `Bearer ${token}`,
    });

    return this.http.get<any>(`${this.apiUrl}/usuario/${login}`, {headers});
  }

  salvarVaga(vaga: CadastroVagaDto, idRecrutador: number): Observable<any> {
    const token = localStorage.getItem('token');
    const headers = new HttpHeaders({
      Authorization: `Bearer ${token}`,
    });


    const payload = { ...vaga, idRecrutador };
    console.log('Payload enviado:', payload);

    return this.http.post(`${this.apiUrl}/vaga/save`, payload, {headers});
  }

  excluirVaga(idVaga: number): Observable<void> {
    const token = localStorage.getItem('token');
    const headers = new HttpHeaders({
      Authorization: `Bearer ${token}`,
    });

    const url = `${this.apiUrl}/usuario/vaga/${idVaga}`;
    return this.http.delete<void>(url, { headers });
  }
}
