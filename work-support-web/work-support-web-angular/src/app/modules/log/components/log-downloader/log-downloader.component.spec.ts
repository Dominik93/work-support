import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { LogDownloaderComponent } from './log-downloader.component';

describe('LogDownloaderComponent', () => {
  let component: LogDownloaderComponent;
  let fixture: ComponentFixture<LogDownloaderComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ LogDownloaderComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(LogDownloaderComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
