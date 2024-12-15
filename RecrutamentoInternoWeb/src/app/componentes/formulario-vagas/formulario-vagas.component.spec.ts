import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { FormularioVagasComponent } from './formulario-vagas.component';

describe('FormularioVagasComponent', () => {
  let component: FormularioVagasComponent;
  let fixture: ComponentFixture<FormularioVagasComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ FormularioVagasComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(FormularioVagasComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
