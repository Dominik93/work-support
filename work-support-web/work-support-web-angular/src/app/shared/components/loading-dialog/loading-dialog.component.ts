import { Component, OnInit, Inject } from '@angular/core';
import { MatDialogRef, MAT_DIALOG_DATA } from '@angular/material';

@Component({
  selector: 'app-loading-dialog',
  templateUrl: './loading-dialog.component.html',
  styleUrls: ['./loading-dialog.component.css']
})
export class LoadingDialogComponent {
  
  constructor(public dialogRef: MatDialogRef<LoadingDialogComponent>) {

  }

  onNoClick(): void {
    this.dialogRef.close();
  }

}