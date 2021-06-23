import { TestBed } from '@angular/core/testing';

import { RuleServiceService } from './rule-service.service';

describe('RuleServiceService', () => {
  let service: RuleServiceService;

  beforeEach(() => {
    TestBed.configureTestingModule({});
    service = TestBed.inject(RuleServiceService);
  });

  it('should be created', () => {
    expect(service).toBeTruthy();
  });
});
