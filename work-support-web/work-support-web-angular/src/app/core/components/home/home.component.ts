import { Component, OnInit, AfterViewInit } from '@angular/core';
import { ContextHolderService } from 'src/app/modules/context/services/context-holder.service';
import { ModuleConfig } from 'src/app/modules/context/models/module-config';

@Component({
  selector: 'app-home',
  templateUrl: './home.component.html',
  styleUrls: ['./home.component.css']
})
export class HomeComponent implements OnInit, AfterViewInit {

  constructor(private contextService: ContextHolderService) { }

  ngOnInit() {
  }

  ngAfterViewInit() {
    var moduleConfig = new ModuleConfig;
    moduleConfig.databases = [];
    moduleConfig.defaultDatabase = null;
    this.contextService.setContextual(true);
    this.contextService.setContextDatabase(moduleConfig);
  }

}
