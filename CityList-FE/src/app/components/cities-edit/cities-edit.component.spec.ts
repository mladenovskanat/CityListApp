import { ComponentFixture, TestBed } from '@angular/core/testing';

import { CitiesEditComponent } from './cities-edit.component';

describe('CitiesEditComponent', () => {
  let component: CitiesEditComponent;
  let fixture: ComponentFixture<CitiesEditComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ CitiesEditComponent ]
    })
    .compileComponents();

    fixture = TestBed.createComponent(CitiesEditComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
