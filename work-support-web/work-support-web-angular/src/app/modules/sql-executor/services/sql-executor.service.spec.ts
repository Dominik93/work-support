import { TestBed } from '@angular/core/testing';

import { SqlExecutorService } from './sql-executor.service';

describe('SqlExecutorService', () => {
  beforeEach(() => TestBed.configureTestingModule({}));

  it('should be created', () => {
    const service: SqlExecutorService = TestBed.get(SqlExecutorService);
    expect(service).toBeTruthy();
  });
});
