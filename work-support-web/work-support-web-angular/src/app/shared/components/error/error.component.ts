import { Component, OnInit } from '@angular/core';
import { Subscription } from 'rxjs';
import { ErrorService } from '../../../core/services/error/error.service';
import { MatDialog } from '@angular/material';
import { ErrorDialogComponent } from '../error-dialog/error-dialog.component';

@Component({
  selector: 'app-error',
  templateUrl: './error.component.html',
  styleUrls: ['./error.component.css']
})
export class ErrorComponent implements OnInit {
  private subscription: Subscription;
  private ERROR_DIALOG_ID: string = "ERROR_DIALOG_ID";

  constructor(private errorService: ErrorService,
    public dialog: MatDialog) { }


  ngOnInit() {
    this.subscription = this.errorService.errorState.subscribe(data => {
      console.log(data);
      if (data) {
        console.log("showError");
        this.openDialog();
      } else {
        console.log("closeError");
        this.closeDialog();
      }
    });
  }

  ngOnDestroy() {
    this.subscription.unsubscribe();
  }

  closeDialog(): void {
    this.dialog.getDialogById(this.ERROR_DIALOG_ID).close();
  }
  
  openDialog(): void {
    this.dialog.open(ErrorDialogComponent, {
      id: this.ERROR_DIALOG_ID,
      width: '700px',
      data: this.errorService.currentError
    });
  }
}
