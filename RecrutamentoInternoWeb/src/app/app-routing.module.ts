import {NgModule} from '@angular/core';
import {RouterModule, Routes} from '@angular/router';
import {SelecionarPerfilComponent} from './componentes/selecionar-perfil/selecionar-perfil.component';
import {AuthGuard} from './service/auth.guard';
import {PerfilRecrutadorComponent} from './componentes/perfil-recrutador/perfil-recrutador.component';
import {PerfilCandidatoComponent} from './componentes/perfil-candidato/perfil-candidato.component';
import {LoginComponent} from './componentes/login/login.component';


const routes: Routes = [
  { path: '', component: SelecionarPerfilComponent, pathMatch: 'full' },
  { path: '*', component: SelecionarPerfilComponent },
  { path: 'profile', component: SelecionarPerfilComponent},
  { path: 'login', component: SelecionarPerfilComponent },
  { path: 'login/:userType', component: LoginComponent },


  { path: 'home/recruiter/:login', component: PerfilRecrutadorComponent , canActivate: [AuthGuard] },
  { path: 'home/candidate/:login', component: PerfilCandidatoComponent, canActivate: [AuthGuard]  },


];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule { }
