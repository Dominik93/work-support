import { Injectable } from '@angular/core';
import { HttpClientService } from 'src/app/core/services/http-client/http-client.service';
import { environment } from 'src/environments/environment';
import { ContextHolderService } from './context-holder.service';
import { Context } from '../models/context';
import { ChangeContextRequest } from '../models/change-context-request';
import { InitResponse } from '../models/init-response';
import { ConfigService } from './config-service';

@Injectable({
  providedIn: 'root'
})
export class ContextService {

  private contextPath: string = environment.baseUrl + "/context";

  constructor(private http: HttpClientService,
    private configService: ConfigService,
    private contextHolder: ContextHolderService) {
    console.log("ContextService constructor");
  }

  async initApplication() {
    console.log("ContextService initApplication");
    var data = await this.init().toPromise();
    var context = new Context();
    context.environment = data.config.defaultEnvironment;
    context.database = null;
    context.databases = [];
    context.actions = data.actions;
    context.moduleActions = data.moduleActions;
    this.configService.config = data.config;
    this.contextHolder.setContext(context);

  }

  changeContext(request: ChangeContextRequest) {
    return this.http.post<InitResponse>(this.contextPath + "/change", request);
  }

  init() {
    console.log("ContextService init");
    return this.http.get<InitResponse>(this.contextPath + "/init");
  }

}
