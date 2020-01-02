import { Component, OnInit, Inject } from '@angular/core';
import { MatDialogRef, MAT_DIALOG_DATA } from '@angular/material';
import { DialogContentData } from '../../models/dialog-content-data';
import { interval } from 'rxjs';
import { ScriptExecutorService } from '../../services/script-executor.service';

@Component({
  selector: 'app-content-dialog',
  templateUrl: './content-dialog.component.html',
  styleUrls: ['./content-dialog.component.css']
})
export class ContentDialogComponent {
  
  content: string = "Wait...";
  allFetched = false;
  timerSubscription: any;
  
  constructor(
    private scriptExecutorService: ScriptExecutorService,
    public dialogRef: MatDialogRef<ContentDialogComponent>,
    @Inject(MAT_DIALOG_DATA) public data: DialogContentData) {

      this.timerSubscription = interval(2500).subscribe(val => {
        this.scriptExecutorService.getScriptOutput(data.token).subscribe(data => {
          this.content = data.content;
          if (data.allFetched) {
            this.timerSubscription.unsubscribe();
          }
        })
      });
    }

  onNoClick(): void {
    this.timerSubscription.unsubscribe();
    this.dialogRef.close();
  }
  

}