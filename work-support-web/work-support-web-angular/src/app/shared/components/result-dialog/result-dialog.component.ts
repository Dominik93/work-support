import { Component, Inject } from '@angular/core';
import { MatDialogRef, MAT_DIALOG_DATA } from '@angular/material';
import { DialogResultData } from 'src/app/core/models/dialog-result-data';
import { ResultService } from '../../../core/services/result/result.service';

@Component({
  selector: 'app-result-dialog',
  templateUrl: './result-dialog.component.html',
  styleUrls: ['./result-dialog.component.css']
})
export class ResultDialogComponent {
  
  constructor(
    private resultService: ResultService,
    public dialogRef: MatDialogRef<ResultDialogComponent>,
    @Inject(MAT_DIALOG_DATA) public data: DialogResultData) {}

  onNoClick(): void {
    this.resultService.hideDialog();
    this.dialogRef.close();
  }

}