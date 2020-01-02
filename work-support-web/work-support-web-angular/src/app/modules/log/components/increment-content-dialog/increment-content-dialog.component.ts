import { Component, OnInit, Inject } from '@angular/core';
import { MatDialogRef, MAT_DIALOG_DATA } from '@angular/material';
import { interval } from 'rxjs';
import { saveAs } from 'file-saver';
import { DialogContentData } from 'src/app/modules/script-executor/models/dialog-content-data';
import { LogDownloaderService } from '../../services/log-downloader.service';
import { StopLiveLogRequest } from '../../models/stop-live-log-request';
import { LiveLog } from '../../models/live-log';
import { CreateJiraBugRequest } from '../../models/create-jira-bug-request';
import { ContextHolderService } from 'src/app/modules/context/services/context-holder.service';

@Component({
  selector: 'app-content-dialog',
  templateUrl: './increment-content-dialog.component.html',
  styleUrls: ['./increment-content-dialog.component.css']
})
export class IncrementContentDialogComponent {

  token: string;
  content: string = "";
  timerSubscription: any;
  jiraBug: boolean;
  liveLogEntries: LiveLog[] = [];

  constructor(
    private logDownloaderService: LogDownloaderService,
    private contextService: ContextHolderService,
    public dialogRef: MatDialogRef<IncrementContentDialogComponent>,
    @Inject(MAT_DIALOG_DATA) public data: DialogContentData) {
    dialogRef.disableClose = true;
    this.token = data.token;
    this.jiraBug = this.contextService.currentContext.actions.logActions.packageLiveLog;
    contextService.getContext().subscribe(data => {
      this.jiraBug = data.actions.logActions.packageLiveLog;
    })
    this.timerSubscription = interval(2500).subscribe(val => {
      this.logDownloaderService.getLiveLog(this.token).subscribe(data => {
        this.liveLogEntries.indexOf
        data.liveLogEntries.forEach(response => {
          var log = this.getLiveLogEntry(response.application);
          if (log === null || log === undefined) {
            var liveLog = new LiveLog();
            liveLog.application = response.application;
            liveLog.content = response.content;
            this.liveLogEntries.push(liveLog);
          } else {
            log.content = log.content + response.content;
          }
        }
        );
      })
    });
  }

  private getLiveLogEntry(application: string): LiveLog {
    var liveLog;
    this.liveLogEntries.forEach(log => {
      if (log.application === application) {
        liveLog = log;
      }
    });
    return liveLog;
  }

  onCreateJiraBug() {
    var request = new CreateJiraBugRequest();
    request.liveLogEntries = this.liveLogEntries;
    this.logDownloaderService.createJiraBug(request).subscribe(data => {
      console.log(data);
      saveAs(data, "logs.zip");
    });
  }

  onNoClick(): void {
    this.timerSubscription.unsubscribe();
    this.dialogRef.close();
    var request = new StopLiveLogRequest();
    request.token = this.token;
    this.logDownloaderService.stopLiveLog(request).subscribe(data => {
      console.log(data.success);
    })
  }


}
