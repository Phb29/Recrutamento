import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { ListaVagasCandidatoComponent } from './lista-vagas-candidato.component';

describe('ListaVagasCandidatoComponent', () => {
  let component: ListaVagasCandidatoComponent;
  let fixture: ComponentFixture<ListaVagasCandidatoComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ ListaVagasCandidatoComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(ListaVagasCandidatoComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
