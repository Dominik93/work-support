import { NgModule } from '@angular/core';

import { CommonModule } from '@angular/common';
import { FormsModule } from '@angular/forms';
import { BrowserAnimationsModule } from '@angular/platform-browser/animations';
import { MaterialModule } from '../material/material.module';
import { SqlResultDialogComponent } from './components/sql-result-dialog/sql-result-dialog.component';
import { SqlExecutorComponent } from './components/sql-executor/sql-executor.component';

@NgModule({
  declarations: [
    SqlExecutorComponent,
    SqlResultDialogComponent,
  ],
  entryComponents:[
    SqlResultDialogComponent
  ],
  imports: [
    CommonModule,
    FormsModule,
    MaterialModule,
    BrowserAnimationsModule
  ]
})
export class SqlExecutorModule { }
