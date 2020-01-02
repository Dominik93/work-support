import { TestBed } from '@angular/core/testing';

import { ScriptExecutorService } from './script-executor.service';

describe('ScriptExecutorService', () => {
  beforeEach(() => TestBed.configureTestingModule({}));

  it('should be created', () => {
    const service: ScriptExecutorService = TestBed.get(ScriptExecutorService);
    expect(service).toBeTruthy();
  });
});
