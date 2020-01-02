import { TestBed } from '@angular/core/testing';

import { LogDownloaderService } from './log-downloader.service';

describe('LogDownloaderService', () => {
  beforeEach(() => TestBed.configureTestingModule({}));

  it('should be created', () => {
    const service: LogDownloaderService = TestBed.get(LogDownloaderService);
    expect(service).toBeTruthy();
  });
});
