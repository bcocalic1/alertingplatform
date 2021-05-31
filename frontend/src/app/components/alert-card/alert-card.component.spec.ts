import { ComponentFixture, TestBed } from '@angular/core/testing';

import { AlertCardComponent } from './alert-card.component';

describe('AlertCardComponent', () => {
  let component: AlertCardComponent;
  let fixture: ComponentFixture<AlertCardComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ AlertCardComponent ]
    })
    .compileComponents();
  });

  beforeEach(() => {
    fixture = TestBed.createComponent(AlertCardComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
