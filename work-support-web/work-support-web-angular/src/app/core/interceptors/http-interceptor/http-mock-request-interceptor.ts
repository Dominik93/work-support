
import { Injectable, Injector } from '@angular/core';
import { HttpEvent, HttpHandler, HttpInterceptor, HttpRequest, HttpResponse } from '@angular/common/http';
import { Observable, of } from 'rxjs';
import * as init from '../../../mocks/init.json';

import * as changeContextEnv1 from '../../../mocks/change-context-env1.json';
import * as changeContextEnv2 from '../../../mocks/change-context-env2.json';
import * as changeContextEnv3 from '../../../mocks/change-context-env3.json';
import * as executeSqlResponse from '../../../mocks/execute-sql-response.json';
import * as executeScriptResponse from '../../../mocks/execute-script-response.json';
import * as getScriptsResponse from '../../../mocks/get-scripts-response.json';
import * as getScriptOutputResponse from '../../../mocks/get-script-output-response.json';
import * as getSqlReposne from '../../../mocks/get-sql-response.json';
import { environment } from 'src/environments/environment';

const changeContext = {
    url: environment.baseUrl + '/context/change',
    json: {
        'ENV1': {
            'POST': changeContextEnv1

        },
        'ENV2': {
            'POST': changeContextEnv2

        },
        'ENV3': {
            'POST': changeContextEnv3
        },
    }
}


const urls = [
    {
        url: environment.baseUrl + '/context/init',
        json: {
            '': {
                '': {
                    'GET': init
                }
            },
        }
    },
    {
        url: environment.baseUrl + '/sql-executor',
        json: {
            'ENV1': {
                'any': {
                    'GET': getSqlReposne,
                    'POST': executeSqlResponse
                }
            }
        }

    },
    {
        url: environment.baseUrl + '/scripts',
        json: {
            'ENV2': {
                'any': {
                    'GET': getScriptsResponse,
                    'POST': executeScriptResponse
                }
            }
        }
    },
    {
        url: environment.baseUrl + '/scripts/output/token_1',
        json: {
            'ENV2': {
                'any': {
                    'GET': getScriptOutputResponse,
                }
            }
        }
    }
];

@Injectable()
export class HttpMockRequestInterceptor implements HttpInterceptor {
    constructor(private injector: Injector) { }

    handleChangeContext(request: HttpRequest<any>): Observable<HttpEvent<any>> {
        var env = request.body.environment;        
        console.log('Loaded from json: ' + request.url + ' for ' + env);
        return of(new HttpResponse({ status: 200, body: ((changeContext.json[env][request.method]) as any).default }));

    }

    handleDefaultRequest(request: HttpRequest<any>): Observable<HttpEvent<any>> {
        for (const element of urls) {
            var tenant = request.headers.get("X-TenantID");
            var env = '';
            var database = '';
            if (tenant !== '') {
                env = tenant.split('-')[0]
                database = tenant.split("-")[1]
            }
            if (request.url === element.url) {
                console.log('Loaded from json: ' + request.url + ' for ' + env + "-" + database);
                var any = element.json[env]['any'];
                var specific = element.json[env][database];
                var response;
                if (specific === undefined) {
                    response = any;
                }
                else {
                    response = specific;
                }
                return of(new HttpResponse({ status: 200, body: ((response[request.method]) as any).default }));
            }
        }
    };

    intercept(request: HttpRequest<any>, next: HttpHandler): Observable<HttpEvent<any>> {
        console.log("Delay 10 sec")
        this.delay(10000);
        if (request.url === changeContext.url) {
            return this.handleChangeContext(request);
        } else {
            return this.handleDefaultRequest(request)
        }
    }

    private delay(ms: number) {
        return new Promise(resolve => setTimeout(resolve, ms));
    }
}


