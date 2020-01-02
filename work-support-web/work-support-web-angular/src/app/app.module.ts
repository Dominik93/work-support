import { BrowserModule } from '@angular/platform-browser';
import { NgModule, APP_INITIALIZER } from '@angular/core';

import { AppRoutingModule } from './app-routing.module';
import { AppComponent } from './app.component';
import { BrowserAnimationsModule } from '@angular/platform-browser/animations';
import { CoreModule } from './core/core.module';
import { MaterialModule } from './modules/material/material.module';
import { FormsModule } from '@angular/forms';
import { HttpClientModule, HTTP_INTERCEPTORS } from '@angular/common/http';
import { HttpModule } from '@angular/http';
import { ContextService } from './modules/context/services/context.service';
import { HttpMockRequestInterceptor } from './core/interceptors/http-interceptor/http-mock-request-interceptor';

import { environment } from '../environments/environment';
import { HttpRequestInterceptor } from './core/interceptors/http-interceptor/http-request-interceptor';
import { SqlExecutorModule } from './modules/sql-executor/sql-executor.module';
import { ScriptExecutorModule } from './modules/script-executor/script-executor.module';
import { LogModule } from './modules/log/log.module';
@NgModule({
  declarations: [
    AppComponent
  ],
  imports: [
    BrowserModule,
    BrowserAnimationsModule,
    AppRoutingModule,
    MaterialModule,
    FormsModule,

    CoreModule,
    SqlExecutorModule,
    ScriptExecutorModule,
    LogModule,

    HttpModule,
    HttpClientModule
  ],
  providers: [
    ContextService,
    { provide: APP_INITIALIZER, useFactory: init, deps: [ContextService], multi: true },
    {
      provide: HTTP_INTERCEPTORS,
      useClass: environment.mock ? HttpMockRequestInterceptor : HttpRequestInterceptor,
      multi: true
    }
  ],
  bootstrap: [AppComponent]
})
export class AppModule { }


export function init(contextService: ContextService) {
  console.log("init application");
  return () => contextService.initApplication();
}
