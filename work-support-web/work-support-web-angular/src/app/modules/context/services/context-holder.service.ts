import { Injectable } from '@angular/core';
import { Subject } from 'rxjs';
import { Module } from 'src/app/core/models/module';
import { Context } from '../models/context';
import { ModuleConfig } from '../models/module-config';

@Injectable({
  providedIn: 'root'
})
export class ContextHolderService {

  private contextual = new Subject<boolean>();

  private context = new Subject<Context>();
  
  currentContext: Context;

  constructor() {
    console.log("ContextService constructor");
  }

  setContextual(contextual: boolean) {
    console.log("set contextual to " + contextual);
    this.contextual.next(contextual);
  }

  getContextual() {
    return this.contextual.asObservable();
  }

  setContext(context: Context) {
    this.currentContext = context;
    this.context.next(context);
  }

  setContextDatabase(moduleConfig: ModuleConfig) {
    this.currentContext.database = moduleConfig.defaultDatabase;
    this.currentContext.databases = moduleConfig.databases;
    this.context.next(this.currentContext);
  }
  
  getContext() {
    return this.context.asObservable();
  }

}
