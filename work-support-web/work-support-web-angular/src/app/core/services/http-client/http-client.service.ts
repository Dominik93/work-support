import { Injectable } from '@angular/core';
import { HttpHeaders, HttpParams, HttpClient, HttpErrorResponse, HttpResponse } from '@angular/common/http';
import { Observable } from 'rxjs';
import { catchError, finalize } from 'rxjs/operators';
import { throwError } from 'rxjs';
import { LoaderService } from '../loader/loader.service';
import { ErrorService } from '../error/error.service';
import { ContextHolderService } from 'src/app/modules/context/services/context-holder.service';

@Injectable({
  providedIn: 'root'
})
export class HttpClientService {

  private environment: string;
  private database: string;

  constructor(private http: HttpClient,
    private contextService: ContextHolderService,
    private loadingService: LoaderService,
    private errorService: ErrorService) {
    contextService.getContext().subscribe(data => {
      this.database = data.database;
      this.environment = data.environment;
    });
  }

  delete<T>(url: string, options?: {
    headers?: HttpHeaders | {
      [header: string]: string | string[];
    };
    observe?: 'body';
    params?: HttpParams | {
      [param: string]: string | string[];
    };
    reportProgress?: boolean;
    responseType?: 'json';
    withCredentials?: boolean;
  }): Observable<T> {
    this.showLoader();
    let headers: HttpHeaders = new HttpHeaders();
    options = this.createOption(options);
    options.headers = this.createTenant(headers);
    return this.http.delete<T>(url, options)
      .pipe(
        catchError(err => this.handleError(err)),
        finalize(() => this.handleFinally())
      );
  }

  get<T>(url: string, options?: {
    headers?: HttpHeaders | {
      [header: string]: string | string[];
    };
    observe?: 'body';
    params?: HttpParams | {
      [param: string]: string | string[];
    };
    reportProgress?: boolean;
    responseType?: 'json';
    withCredentials?: boolean;
  }): Observable<T> {
    this.showLoader();
    let headers: HttpHeaders = new HttpHeaders();
    options = this.createOption(options);
    options.headers = this.createTenant(headers);
    return this.http.get<T>(url, options)
      .pipe(
        catchError(err => this.handleError(err)),
        finalize(() => this.handleFinally())
      );
  }

  download(url: string, body: any | null, options?: {
    headers?: HttpHeaders | {
      [header: string]: string | string[];
    };
    observe: 'response';
    params?: HttpParams | {
      [param: string]: string | string[];
    };
    reportProgress?: boolean;
    responseType: 'blob';
    withCredentials?: boolean;
  }): Observable<HttpResponse<Blob>> {
    let headers: HttpHeaders = new HttpHeaders();
    options = this.createOption(options);
    options.headers = this.createTenant(headers);
    return this.http.post(url, body, options);
    
  }

  downloadFile(url: string, body: any | null, options: {
    headers?: HttpHeaders | {
      [header: string]: string | string[];
    };
    observe?: 'body';
    params?: HttpParams | {
      [param: string]: string | string[];
    };
    reportProgress?: boolean;
    responseType: 'blob';
    withCredentials?: boolean;
  }): Observable<Blob> {
    this.showLoader();
    let headers: HttpHeaders = new HttpHeaders();
    options = this.createOption(options);
    options.headers = this.createTenant(headers);
    return this.http.post(url, body, options)
      .pipe(
        catchError(err => this.handleError(err)),
        finalize(() => this.handleFinally())
      );
  }

  post<T>(url: string, body: any | null, options?: {
    headers?: HttpHeaders | {
      [header: string]: string | string[];
    };
    observe?: 'body';
    params?: HttpParams | {
      [param: string]: string | string[];
    };
    reportProgress?: boolean;
    responseType?: 'json';
    withCredentials?: boolean;
  }): Observable<T> {
    this.showLoader();
    let headers: HttpHeaders = new HttpHeaders();
    options = this.createOption(options);
    options.headers = this.createTenant(headers);
    return this.http.post<T>(url, body, options)
      .pipe(
        catchError(err => this.handleError(err)),
        finalize(() => this.handleFinally())
      );
  }

  createOption(options: any) {
    if (options === undefined) {
      options = {};
      options.headers = [];
    }
    return options;
  }

  createTenant(headers: HttpHeaders) {
    var tenant = "";
    if (this.environment !== undefined && this.environment !== null && this.environment !== '') {
      tenant = this.environment +"-";
    }
    if (this.database !== undefined && this.database !== null && this.database !== '') {
      tenant += this.database;
    }
    console.log("Send request with tenant " + tenant)
    return headers.append('X-TenantID', tenant);
  }

  private handleFinally() {
    this.loadingService.hide();
  }

  private handleError(error: Response | any) {
    console.log('Show Error' + error.error);
    this.errorService.show(error.error);
    return throwError('Error');
  }

  private showLoader(): void {
    this.loadingService.show();
  }

}
