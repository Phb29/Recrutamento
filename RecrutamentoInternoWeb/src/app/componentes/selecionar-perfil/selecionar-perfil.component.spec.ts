import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { SelecionarPerfilComponent } from './selecionar-perfil.component';

describe('SelecionarPerfilComponent', () => {
  let component: SelecionarPerfilComponent;
  let fixture: ComponentFixture<SelecionarPerfilComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ SelecionarPerfilComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(SelecionarPerfilComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
