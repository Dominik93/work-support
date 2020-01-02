import { Injectable } from '@angular/core';
import { HttpClientService } from 'src/app/core/services/http-client/http-client.service';
import { DownloadLogRequest } from '../models/download-log-request';
import { GetApplicationsResponse } from '../models/get-applications-response';
import { HttpHeaders } from '@angular/common/http';
import { environment } from 'src/environments/environment';
import { InitLiveLogRequest } from '../models/init-live-log-request';
import { LiveLogResponse } from '../models/live-log-response';
import { InitLiveLogResponse } from '../models/init-live-log-response';
import { StopLiveLogRequest } from '../models/stop-live-log-request';
import { StopLiveLogResponse } from '../models/stop-live-log-response';
import { CreateJiraBugRequest } from '../models/create-jira-bug-request';

@Injectable({
  providedIn: 'root'
})
export class LogDownloaderService {

  private logPath: string = environment.baseUrl + "/log";

  constructor(private http: HttpClientService) {
    console.log("LogDownloaderService constructor");
  }

  getApplications() {
    return this.http.get<GetApplicationsResponse>(this.logPath + "/applications");
  }

  downloadLog(request: DownloadLogRequest) {
    return this.http.downloadFile(this.logPath + "/download", request, {
      responseType: "blob",
      headers: new HttpHeaders().append("Content-Type", "application/json")
    });
  }

  initLiveLog(request: InitLiveLogRequest) {
    return this.http.post<InitLiveLogResponse>(this.logPath + "/live", request);
  }

  getLiveLog(token: string) {
    return this.http.get<LiveLogResponse>(this.logPath + "/live/" + token);
  }

  stopLiveLog(request: StopLiveLogRequest) {
    return this.http.post<StopLiveLogResponse>(this.logPath + "/live/stop", request);
    // TODO czemu nie można dać delete? leci Options 403
  }

  createJiraBug(request: CreateJiraBugRequest) {
    return this.http.downloadFile(this.logPath + "/live/jira/create", request, {
      responseType: "blob",
      headers: new HttpHeaders().append("Content-Type", "application/json")
    });
  }


}
