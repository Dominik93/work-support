import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { SqlResultDialogComponent } from './sql-result-dialog.component';

describe('SqlResultDialogComponent', () => {
  let component: SqlResultDialogComponent;
  let fixture: ComponentFixture<SqlResultDialogComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ SqlResultDialogComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(SqlResultDialogComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
