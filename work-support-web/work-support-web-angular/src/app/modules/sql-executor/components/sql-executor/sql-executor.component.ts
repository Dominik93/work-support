import { Component, OnInit, AfterViewInit, OnDestroy } from '@angular/core';
import { SqlExecutorService } from '../../services/sql-executor.service';
import { SqlMetadata } from '../../models/sql-metadata';
import { ExecuteSqlRequest } from '../../models/execute-sql-request';
import { MatTableDataSource, MatDialog } from '@angular/material';
import { ExecuteSqlResponse } from '../../models/execute-sql-response';
import { SqlResultDialogComponent } from '../sql-result-dialog/sql-result-dialog.component';
import { Subscription } from 'rxjs';
import { Module } from 'src/app/core/models/module';
import { ContextHolderService } from 'src/app/modules/context/services/context-holder.service';
import { ConfigService } from 'src/app/modules/context/services/config-service';
import { Router } from '@angular/router';
import { ModuleConfig } from 'src/app/modules/context/models/module-config';

@Component({
  selector: 'app-sql-executor',
  templateUrl: './sql-executor.component.html',
  styleUrls: ['./sql-executor.component.css']
})
export class SqlExecutorComponent implements OnInit, AfterViewInit, OnDestroy {

  displayedColumns: string[] = ['name', 'description', 'parameters', 'run'];
  contextSubscription: Subscription;
  dataSource: MatTableDataSource<SqlMetadata>;

  constructor(private sqlExecutorService: SqlExecutorService,
    private contextHolderService: ContextHolderService,
    private configService: ConfigService,
    private router: Router,
    public dialog: MatDialog) { }

  ngOnInit() {
    this.contextHolderService.setContextDatabase(this.configService.config.sqlExecutor);
    this.initSql();
    this.contextSubscription = this.contextHolderService.getContext().subscribe(data => {
      if (data.moduleActions.sqlExecutor) {
        this.initSql();
      } else {    
        this.router.navigateByUrl('');
      }
    });
  }

  initSql() {
    this.sqlExecutorService.getSql().subscribe(data => {
      this.dataSource = new MatTableDataSource(data.sqlMetadatas);
    });
  }

  ngOnDestroy() {
    this.contextSubscription.unsubscribe();
  }


  ngAfterViewInit() {
    this.contextHolderService.setContextual(true);
  }

  onRun(sql: SqlMetadata) {
    var request = new ExecuteSqlRequest();
    request.sql = sql;
    this.sqlExecutorService.executeSql(request).subscribe(data => {
      this.openSqlResultDialog(data);
    });
  }

  openSqlResultDialog(response: ExecuteSqlResponse): void {
    this.dialog.open(SqlResultDialogComponent, {
      width: '1900px',
      data: { rows: response.rows, headers: response.headers }
    });
  }


  applyFilter(filterValue: string) {
    this.dataSource.filter = filterValue.trim().toLowerCase();
  }


}
