import { ComponentFixture, TestBed } from '@angular/core/testing';

import { NewRuleButtonComponent } from './new-rule-button.component';

describe('NewRuleButtonComponent', () => {
  let component: NewRuleButtonComponent;
  let fixture: ComponentFixture<NewRuleButtonComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ NewRuleButtonComponent ]
    })
    .compileComponents();
  });

  beforeEach(() => {
    fixture = TestBed.createComponent(NewRuleButtonComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
