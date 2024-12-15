import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Observable } from 'rxjs';
import {LoginDto} from '../../classe/login.dto';
import {environment} from '../../environments/environment';


@Injectable({
  providedIn: 'root',
})
export class LoginService {
  private apiUrl = environment.apiUrl;

  constructor(private http: HttpClient) {}


  login(credencial: LoginDto): Observable<any> {
    return this.http.post<any>(`${this.apiUrl}/auth/login`, credencial);
  }


  createAccount(credencial: LoginDto): Observable<any> {
    return this.http.post<any>(`${this.apiUrl}/usuario`, credencial);
  }
}
