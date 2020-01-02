import { NgModule } from '@angular/core';

import { CommonModule } from '@angular/common';
import { MaterialModule } from '../material/material.module';
import { FormsModule } from '@angular/forms';
import { ScriptExecutorComponent } from './components/script-executor/script-executor.component';
import { BrowserAnimationsModule } from '@angular/platform-browser/animations';
import { ContentDialogComponent } from './components/content-dialog/content-dialog.component';

@NgModule({
  declarations: [
    ScriptExecutorComponent,
    ContentDialogComponent
  ],  
  entryComponents:[
    ContentDialogComponent
  ],
  imports: [
    CommonModule,
    FormsModule,
    MaterialModule,
    BrowserAnimationsModule
  ]
})
export class ScriptExecutorModule { }
