import {Component, OnInit, ViewEncapsulation} from '@angular/core';
import {AbstractControl, FormBuilder, FormGroup, ValidationErrors, ValidatorFn, Validators} from '@angular/forms';
import {ActivatedRoute, Router} from '@angular/router';
import {AuthService} from '../../service/auth.service';
import {MessageService} from 'primeng/api';
import {LoginDto} from '../../../classe/login.dto';

import {LoginService} from '../../service/login.service';

@Component({
  selector: 'app-login',
  templateUrl: './login.component.html',
  styleUrls: ['./login.component.scss'],
  encapsulation: ViewEncapsulation.None,
})
export class LoginComponent implements OnInit {

  constructor(private fb: FormBuilder,
              private messageService: MessageService,
              private router: Router,
              private activatedRoute: ActivatedRoute,
              private authService: AuthService,
              private loginService: LoginService) {
    this.sexOptions = [{label: 'M', value: 'M'}, {label: 'F', value: 'F'}];

  }

  sexOptions: any[];
  newUserForm: FormGroup = new FormGroup({});
  newUser = false;
  userType = 'candidate';
  credencial = new LoginDto();
  nome: string | number | null = '';


  ngOnInit(): void {
    this.credencial.tipoUsuario = this.activatedRoute.snapshot.paramMap.get('userType');
    this.newUserForm = this.fb.group({
      nome: ['', [Validators.required, Validators.minLength(10)]],
      login: ['', [Validators.required, this.alphanumericValidator()]],
      email: ['', [Validators.required, Validators.email]],
      newPassword: ['', [Validators.required]],
      checkNewPassword: ['', [Validators.required]],
      sexo: ['M', []]
    }, {validator: this.passwordMatchValidator});
  }

  alphanumericValidator(): ValidatorFn {
    return (control: AbstractControl): ValidationErrors | null => {
      const isValid = /^[a-zA-Z0-9]*$/.test(control.value);
      return isValid ? null : {alphanumeric: true};
    };
  }

  passwordMatchValidator(form: FormGroup) {
    return form.get('newPassword').value === form.get('checkNewPassword').value
      ? null : {mismatch: true};
  }

  onSubmit() {
    if (this.newUserForm.valid) {
      this.credencial.nome = this.newUserForm.get('nome').value;
      this.credencial.login = this.newUserForm.get('login').value;
      this.credencial.email = this.newUserForm.get('email').value;
      this.credencial.senha = this.newUserForm.get('newPassword').value;
      this.credencial.sexo = this.newUserForm.get('sexo').value;

      this.criarLogin();

    }
  }

  login(): void {
    if (!this.ValidarCredencial()) {
      return;
    }

    this.loginService.login(this.credencial).subscribe({
      next: (retorno: any) => {
        if (retorno.token && retorno.id) {
          // Salva o token e o ID do usuário no localStorage
          this.authService.setToken(retorno.token);
          localStorage.setItem('idUsuario', retorno.id.toString());

          console.log('Token e ID do usuário salvo no localStorage:', retorno.token, retorno.id);

          // Redireciona o usuário para a página inicial
          this.router.navigate(['/home', this.credencial.tipoUsuario, this.credencial.login]);

          // Mostra mensagem de sucesso
          this.messageService.add({
            severity: 'success',
            summary: retorno.message || 'Sucesso',
            detail: retorno.description || 'Login efetuado com sucesso!',
            life: 2000
          });
        } else {
          this.messageService.add({
            severity: 'error',
            summary: 'Erro no login',
            detail: 'Token ou ID do usuário não retornado pelo servidor.',
            life: 2000
          });
        }
      },
      error: (err) => {
        console.error('Erro no login:', err);
        this.messageService.add({
          severity: 'error',
          summary: 'Usuário ou senha inválidos',
          detail: 'Verifique suas credenciais.',
          life: 2000
        });
      }
    });
  }



  criarLogin(): void {
    if (this.newUserForm.valid) {
      this.credencial.nome = this.newUserForm.get('nome').value;
      this.credencial.login = this.newUserForm.get('login').value;
      this.credencial.email = this.newUserForm.get('email').value;
      this.credencial.senha = this.newUserForm.get('newPassword').value;
      this.credencial.sexo = this.newUserForm.get('sexo').value;

      this.loginService.createAccount(this.credencial).subscribe({
        next: (retorno: any) => {
          if (retorno.status === 200) {
            this.authService.setToken(retorno.token);
            this.newUser = false; // Muda o modo do formulário para login

            this.messageService.add({
              severity: 'success',
              summary: retorno.message || 'Conta criada com sucesso!',
              detail: retorno.description || 'Usuário cadastrado com sucesso.',
              life: 2000
            });
          } else {
            this.messageService.add({
              severity: 'error',
              summary: retorno.message || 'Erro ao criar conta',
              detail: retorno.description || 'Não foi possível criar a conta.',
              life: 3000
            });
          }
        },
        error: (err) => {
          console.error('Erro ao criar conta:', err);
          this.messageService.add({
            severity: 'error',
            summary: 'Erro no servidor',
            detail: 'Tente novamente mais tarde.',
            life: 3000
          });
        }
      });
    } else {
      this.messageService.add({
        severity: 'warn',
        summary: 'Formulário inválido',
        detail: 'Preencha todos os campos corretamente.',
        life: 3000
      });
    }
  }

  changeNewUser() {
    this.newUser = !this.newUser;
  }

  private valid(atributo: string | undefined) {
    return atributo === undefined || atributo === null || atributo.length <= 1;
  }

  private ValidarCredencial(): boolean {
    if (this.valid(this.credencial.login) || this.valid(this.credencial.senha)) {
      if (this.valid(this.credencial.login) && this.valid(this.credencial.senha)) {
        this.messageService.add({
          severity: 'warn',
          summary: 'O Usuário e  Senha Obrigatório !',
          life: 2000
        });
        return false;
      } else if (this.valid(this.credencial.login)) {
        this.messageService.add({
          severity: 'warn',
          summary: 'O Usuário é obrigatório!',
          life: 2000
        });
        return false;
      } else if (this.valid(this.credencial.senha)) {
        this.messageService.add({
          severity: 'warn',
          summary: 'A Senha é obrigatória',
          life: 2000
        });
        return false;
      }
    }
    return true;

  }

}
