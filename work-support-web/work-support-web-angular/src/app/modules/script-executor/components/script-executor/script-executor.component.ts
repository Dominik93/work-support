import { Component, OnInit, OnDestroy, AfterViewInit } from '@angular/core';
import { ScriptExecutorService } from '../../services/script-executor.service';
import { ExecuteScriptRequest } from '../../models/execute-script-request';
import { Subscription } from 'rxjs';
import { MatTableDataSource, MatDialog } from '@angular/material';
import { ContentDialogComponent } from '../content-dialog/content-dialog.component';
import { ContextHolderService } from 'src/app/modules/context/services/context-holder.service';
import { ConfigService } from 'src/app/modules/context/services/config-service';
import { Router } from '@angular/router';
import { Script } from '../../models/script';

@Component({
  selector: 'app-script-executor',
  templateUrl: './script-executor.component.html',
  styleUrls: ['./script-executor.component.css']
})
export class ScriptExecutorComponent implements OnInit, OnDestroy, AfterViewInit {

  displayedColumns: string[] = ['name', 'options', 'execute', 'showLog'];

  contextSubscription: Subscription;

  dataSource: MatTableDataSource<Script>;

  constructor(private scriptExecutorService: ScriptExecutorService,
    private contextHolderService: ContextHolderService,
    private configService: ConfigService,
    private router: Router,
    public dialog: MatDialog) { }

  ngOnInit() {
    this.contextHolderService.setContextDatabase(this.configService.config.scriptExecutor);
    this.initScripts();
    this.contextSubscription = this.contextHolderService.getContext().subscribe(data => {
      this.initScripts();
    });
  }

  ngAfterViewInit() {
    this.contextHolderService.setContextual(true);
  }

  initScripts() {
    if (this.contextHolderService.currentContext.moduleActions.scriptExecutor) {
      this.scriptExecutorService.getScripts().subscribe(data => {
        this.dataSource = new MatTableDataSource(data.scripts);
      });
    } else {
      this.router.navigateByUrl('');
    }
  }

  onExecute(script: Script) {
    console.log("execute " + script);
    var request = new ExecuteScriptRequest();
    request.script = script;
    this.scriptExecutorService.executeScript(request).subscribe(data => {
      script.token = data.token;
      script.executed = true;
    });
  }

  onShowLog(token: string) {
    this.scriptExecutorService.getScriptOutput(token).subscribe(data => {
      this.openContentDialog(token);
    })
  }

  openContentDialog(token: string): void {
    this.dialog.open(ContentDialogComponent, {
      width: '1000px',
      data: { token: token }
    });
  }

  ngOnDestroy() {
    this.contextSubscription.unsubscribe();
  }

}
