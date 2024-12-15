import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { ListaVagasCandidatoAplicarComponent } from './lista-vagas-candidato-aplicar.component';

describe('ListaVagasCandidatoAplicarComponent', () => {
  let component: ListaVagasCandidatoAplicarComponent;
  let fixture: ComponentFixture<ListaVagasCandidatoAplicarComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ ListaVagasCandidatoAplicarComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(ListaVagasCandidatoAplicarComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
