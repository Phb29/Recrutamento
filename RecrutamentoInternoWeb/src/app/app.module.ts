import { BrowserModule } from '@angular/platform-browser';
import { NgModule } from '@angular/core';

import { AppComponent } from './app.component';
import { BrowserAnimationsModule } from '@angular/platform-browser/animations';
import {RouterModule} from '@angular/router';
import { PerfilCandidatoComponent } from './componentes/perfil-candidato/perfil-candidato.component';
import { LoginComponent } from './componentes/login/login.component';
import { PerfilRecrutadorComponent } from './componentes/perfil-recrutador/perfil-recrutador.component';
import { SelecionarPerfilComponent } from './componentes/selecionar-perfil/selecionar-perfil.component';
import { FormularioVagasComponent } from './componentes/formulario-vagas/formulario-vagas.component';
import { ListaVagasComponent } from './componentes/lista-vagas/lista-vagas.component';
import { ListaVagasCandidatoComponent } from './componentes/lista-vagas-candidato/lista-vagas-candidato.component';
import {AppRoutingModule} from './app-routing.module';

import {
  ButtonModule,
  CheckboxModule,
  ChipsModule,
  DialogModule, EditorModule,
  GMapModule,
  InputMaskModule, InputTextareaModule,
  MenubarModule, SelectButtonModule,
  TabMenuModule, ToggleButtonModule
} from 'primeng/primeng';
import {HttpClientJsonpModule, HttpClientModule} from '@angular/common/http';
import {TableModule} from 'primeng/table';
import {FormsModule, ReactiveFormsModule} from '@angular/forms';
import {CommonModule} from '@angular/common';
import {ToastModule} from 'primeng/toast';
import {MessageService} from 'primeng/api';
import { ListaVagasCandidatoAplicarComponent } from './componentes/lista-vagas-candidato-aplicar/lista-vagas-candidato-aplicar.component';
import {
  MatButtonModule,
  MatCardModule, MatDialogModule,
  MatFormField,
  MatFormFieldModule,
  MatGridListModule,
  MatIconModule,
  MatInput, MatInputModule, MatListModule, MatSidenavModule, MatSnackBarModule
} from '@angular/material';
@NgModule({
  declarations: [
    AppComponent,
    PerfilCandidatoComponent,
    LoginComponent,
    PerfilRecrutadorComponent,
    SelecionarPerfilComponent,
    FormularioVagasComponent,
    ListaVagasComponent,
    ListaVagasCandidatoComponent,
    ListaVagasCandidatoAplicarComponent
  ],
    imports: [
        BrowserModule,
        BrowserAnimationsModule,
        RouterModule,
      AppRoutingModule,
      TabMenuModule,
      ButtonModule,
      HttpClientModule,
      TableModule,
      BrowserAnimationsModule,
      FormsModule,
      MenubarModule,
      ChipsModule,
      InputMaskModule,
      GMapModule,
      CommonModule,
      HttpClientJsonpModule,
      DialogModule,
      CheckboxModule,
      ToggleButtonModule,
      ToastModule,
      InputTextareaModule,
      EditorModule,
      ReactiveFormsModule,
      SelectButtonModule,
      MatCardModule,
      MatGridListModule,
      MatButtonModule,
      MatIconModule,
      MatFormFieldModule,
      MatCardModule,
      MatGridListModule,
      MatButtonModule,
      MatIconModule,
      MatFormFieldModule,
      MatInputModule,
      MatListModule,
      MatSidenavModule,
      MatDialogModule,
      MatSnackBarModule

    ],
  providers: [MessageService],
  bootstrap: [AppComponent]
})
export class AppModule { }
