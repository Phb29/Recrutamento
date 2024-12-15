import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { PerfilRecrutadorComponent } from './perfil-recrutador.component';

describe('PerfilRecrutadorComponent', () => {
  let component: PerfilRecrutadorComponent;
  let fixture: ComponentFixture<PerfilRecrutadorComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ PerfilRecrutadorComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(PerfilRecrutadorComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
