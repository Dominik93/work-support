import { Component, OnInit } from '@angular/core';
import { MatSnackBar, MatDialog } from '@angular/material';
import { ResultSnackbarComponent } from '../result-snackbar/result-snackbar.component';
import { ResultService } from '../../../core/services/result/result.service';
import { ResultDialogComponent } from '../result-dialog/result-dialog.component';

@Component({
  selector: 'app-result',
  templateUrl: './result.component.html',
  styleUrls: ['./result.component.css']
})
export class ResultComponent implements OnInit {

  subscription;

  constructor(private snackBar: MatSnackBar,
    public dialog: MatDialog,
    private resultService: ResultService) { }

  ngOnInit() {
    this.subscription = this.resultService.resultSnackBarState.subscribe(data => {
      console.log("ResultComponent " + data);
      if (data) {
        console.log("show snackBar");
        this.openSnackBar(data);
      } else {
        console.log("close snackBar");
      }
    });
    this.resultService.resultDialogState.subscribe(data => {
      console.log("ResultComponent " + data);
      if (data) {
        console.log("show snackBar");
        this.openDialog(data);
      } else {
        console.log("close snackBar");
      }
    });
  }


  openDialog(success): void {
    this.dialog.open(ResultDialogComponent, {
      width: '250px',
      data: { success: success }
    });
  }

  openSnackBar(data) {
    var announcementMessage = "";
    if(data){
      announcementMessage = "Success"
    } else {
      announcementMessage = "Fail";
    }
    let snackBarRef = this.snackBar.openFromComponent(ResultSnackbarComponent, {
      announcementMessage,
      duration: 5 * 1000,
    });

    snackBarRef.afterDismissed().subscribe(() => {
      this.resultService.hideSnackBar();
    });
  }
}
