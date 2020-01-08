import { Injectable } from '@angular/core';
import { HttpClientService } from 'src/app/core/services/http-client/http-client.service';
import { GetScriptsResponse } from '../models/get-scripts-response';
import { ExecuteScriptResponse } from '../models/execute-script-response';
import { ExecuteScriptRequest } from '../models/execute-script-request';
import { GetScriptOutputResponse } from '../models/get-script-output-response';
import { environment } from 'src/environments/environment';

@Injectable({
  providedIn: 'root'
})
export class ScriptExecutorService {

  private scriptExecutorPath: string = environment.baseUrl + "/script-executor";

  constructor(private http: HttpClientService) {
    console.log("ScriptExecutorService constructor");
  }

  getScripts() {
    return this.http.get<GetScriptsResponse>(this.scriptExecutorPath + "/scripts");
  }

  executeScript(executeScriptRequest: ExecuteScriptRequest) {
    return this.http.post<ExecuteScriptResponse>(this.scriptExecutorPath + "/scripts", executeScriptRequest);
  }

  getScriptOutput(token: string) {
    return this.http.get<GetScriptOutputResponse>(this.scriptExecutorPath + "/scripts/output/" + token);
  }

}
