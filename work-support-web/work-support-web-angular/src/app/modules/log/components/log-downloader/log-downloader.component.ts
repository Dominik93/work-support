import { Component, OnInit, AfterViewInit, Input } from '@angular/core';
import { LogDownloaderService } from '../../services/log-downloader.service';
import { DownloadLogRequest } from '../../models/download-log-request';
import { saveAs } from 'file-saver';
import { InitLiveLogRequest } from '../../models/init-live-log-request';
import { IncrementContentDialogComponent } from '../increment-content-dialog/increment-content-dialog.component';
import { MatDialog } from '@angular/material';
import { ContextHolderService } from 'src/app/modules/context/services/context-holder.service';

@Component({
  selector: 'app-log-downloader',
  templateUrl: './log-downloader.component.html',
  styleUrls: ['./log-downloader.component.css']
})
export class LogDownloaderComponent implements OnInit {

  @Input() timeFrom: string;
  @Input() timeTo: string;
  @Input() applications: string[];
  @Input() date: any;
  @Input() current: boolean;

  queries: string[];
  onlyExcpetions: boolean;
  bufferSize: number = 0;
  token: string;
  liveLogAction: boolean;

  constructor(private logDownloaderService: LogDownloaderService,
    private contextService: ContextHolderService,
    public dialog: MatDialog) { }

  ngOnInit() {
    this.queries = [];
    this.queries.push("");
    this.onlyExcpetions = false;
    this.liveLogAction = this.contextService.currentContext.actions.logActions.liveLog;
    this.contextService.getContext().subscribe(data => {
      this.liveLogAction = data.actions.logActions.liveLog;
    })
  }

  customTrackBy(index: number, obj: any): any {
    return index;
  }
  addQuery() {
    this.queries.push("");
  }

  isEmptyQuery(query: string) {
    return query === "";
  }

  isLastQuery(i) {
    return this.queries.length == (i + 1);
  }

  onLiveLog() {
    console.log("onLiveLog");
    var initRequest = new InitLiveLogRequest();
    initRequest.applications = this.applications;
    initRequest.logQuery.queries = this.queries.filter(query => query != "");
    initRequest.logQuery.onlyException = this.onlyExcpetions;
    initRequest.logQuery.bufferSize = this.bufferSize;
    this.logDownloaderService.initLiveLog(initRequest).subscribe(data => {
      console.log(data);
      this.token = data.token;
      this.openContentDialog(this.token);
    });
  }


  openContentDialog(token: string): void {
    this.dialog.open(IncrementContentDialogComponent, {
      width: '1000px',
      data: { token: token }
    });
  }

  onDownload() {
    console.log("onDownload");
    var downloadLogRequest = new DownloadLogRequest();
    downloadLogRequest.applications = this.applications;
    downloadLogRequest.logQuery.queries = this.queries.filter(query => query != "");
    downloadLogRequest.logQuery.onlyException = this.onlyExcpetions;
    downloadLogRequest.date = this.date.value.toLocaleDateString("en-US");
    downloadLogRequest.from = this.timeFrom;
    downloadLogRequest.to = this.timeTo;
    downloadLogRequest.logQuery.bufferSize = this.bufferSize;
    downloadLogRequest.current = this.current;
    this.logDownloaderService.downloadLog(downloadLogRequest).subscribe(data => {
      console.log(data);
      saveAs(data, this.getMockFileName(data));
    });
  }


  getMockFileName(data) {
    if (this.current) {
      this.contextService.currentContext.environment + "_current.zip";
    }
    return this.contextService.currentContext.environment + "_" +
      this.date.value.toLocaleDateString("pl-PL") + "(" +
      this.timeFrom + "-" + this.timeTo + ").zip";
  }


  getFileNameFromHttpResponse(httpResponse) {
    var contentDispositionHeader = httpResponse.headers('Content-Disposition');
    var result = contentDispositionHeader.split(';')[1].trim().split('=')[1];
    return result.replace(/"/g, '');
  }

}
