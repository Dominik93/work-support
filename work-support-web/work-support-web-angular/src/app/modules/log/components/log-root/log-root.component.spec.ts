import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { LogRootComponent } from './log-root.component';

describe('LogRootComponent', () => {
  let component: LogRootComponent;
  let fixture: ComponentFixture<LogRootComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ LogRootComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(LogRootComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
