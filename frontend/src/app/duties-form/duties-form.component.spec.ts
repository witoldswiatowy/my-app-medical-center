import { ComponentFixture, TestBed } from '@angular/core/testing';

import { DutiesFormComponent } from './duties-form.component';

describe('DutiesFormComponent', () => {
  let component: DutiesFormComponent;
  let fixture: ComponentFixture<DutiesFormComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ DutiesFormComponent ]
    })
    .compileComponents();

    fixture = TestBed.createComponent(DutiesFormComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
