import { Component, OnInit, Inject } from '@angular/core';
import { MatDialogRef, MAT_DIALOG_DATA } from '@angular/material';
import { ErrorService } from '../../../core/services/error/error.service';
import { DialogErrorData } from '../../../core/models/dialog-error-data';

@Component({
  selector: 'app-error-dialog',
  templateUrl: './error-dialog.component.html',
  styleUrls: ['./error-dialog.component.css']
})
export class ErrorDialogComponent {

  constructor(public dialogRef: MatDialogRef<ErrorDialogComponent>,
    @Inject(MAT_DIALOG_DATA) public data: DialogErrorData,
    private errorService: ErrorService) {

  }

  onNoClick(): void {
    this.errorService.hide();
  }

}