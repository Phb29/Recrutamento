import { Component, OnInit } from '@angular/core';
import {Router} from '@angular/router';

@Component({
  selector: 'app-selecionar-perfil',
  templateUrl: './selecionar-perfil.component.html',
  styleUrls: ['./selecionar-perfil.component.scss']
})
export class SelecionarPerfilComponent implements OnInit {

  constructor(private router: Router) { }

  ngOnInit(): void {
  }

  goToLoginPage(userType: 'candidate' | 'recruiter') {
    this.router.navigate(['/login', userType]);
  }

}
