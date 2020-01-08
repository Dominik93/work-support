import { Injectable } from '@angular/core';
import { HttpClientService } from 'src/app/core/services/http-client/http-client.service';
import { GetSqlResponse } from '../models/get-sql-response';
import { ExecuteSqlRequest } from '../models/execute-sql-request';
import { ExecuteSqlResponse } from '../models/execute-sql-response';
import { environment } from 'src/environments/environment';

@Injectable({
  providedIn: 'root'
})
export class SqlExecutorService {


  private sqlExecutorPath: string = environment.baseUrl + "/sql-executor";

  constructor(private http: HttpClientService) {
    console.log("SqlExecutorService constructor");
  }

  getSql() {
    return this.http.get<GetSqlResponse>(this.sqlExecutorPath + "/sql");
  }

  executeSql(request: ExecuteSqlRequest) {
    return this.http.post<ExecuteSqlResponse>(this.sqlExecutorPath + "/sql", request);
  }

}
