import { ComponentFixture, TestBed } from '@angular/core/testing';

import { AllVisitListComponent } from './all-visit-list.component';

describe('AllVisitListComponent', () => {
  let component: AllVisitListComponent;
  let fixture: ComponentFixture<AllVisitListComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ AllVisitListComponent ]
    })
    .compileComponents();

    fixture = TestBed.createComponent(AllVisitListComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
