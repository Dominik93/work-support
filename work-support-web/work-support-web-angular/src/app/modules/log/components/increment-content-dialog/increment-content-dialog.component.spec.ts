import { async, ComponentFixture, TestBed } from '@angular/core/testing';
import { IncrementContentDialogComponent } from './increment-content-dialog.component';


describe('IncrementContentDialogComponent', () => {
  let component: IncrementContentDialogComponent;
  let fixture: ComponentFixture<IncrementContentDialogComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ IncrementContentDialogComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(IncrementContentDialogComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
