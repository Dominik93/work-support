import { Component, OnInit } from '@angular/core';
import { ContextHolderService } from 'src/app/modules/context/services/context-holder.service';
import { Context } from 'src/app/modules/context/models/context';

@Component({
  selector: 'app-menu',
  templateUrl: './menu.component.html',
  styleUrls: ['./menu.component.css']
})
export class MenuComponent implements OnInit {

  sqlExecutor: boolean;

  scriptExecutor: boolean;

  logDownloader: boolean;

  constructor(private contextHolder: ContextHolderService) { }

  ngOnInit() {
    //this.initMenu(this.contextHolder.currentContext);
    this.contextHolder.getContext().subscribe(data => {
      this.initMenu(data);
    })
  }

  initMenu(context: Context) {
    this.sqlExecutor = context.moduleActions.sqlExecutor;
    this.scriptExecutor = context.moduleActions.scriptExecutor;
    this.logDownloader = context.moduleActions.logDownloader;
  }

  redirect(url) {
    console.log(url);
    window.location.href = url;
  }
}
