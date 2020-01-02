import { NgModule } from '@angular/core';

import { CommonModule } from '@angular/common';
import { FormsModule, ReactiveFormsModule } from '@angular/forms';
import { BrowserAnimationsModule } from '@angular/platform-browser/animations';
import { LogDownloaderComponent } from './components/log-downloader/log-downloader.component';
import { LogRootComponent } from './components/log-root/log-root.component';
import { IncrementContentDialogComponent } from './components/increment-content-dialog/increment-content-dialog.component';
import { MaterialModule } from '../material/material.module';

@NgModule({
  declarations: [
    LogRootComponent,
    LogDownloaderComponent,
    IncrementContentDialogComponent
  ],
  entryComponents: [
    IncrementContentDialogComponent
  ],
  imports: [
    CommonModule,
    FormsModule,
    ReactiveFormsModule,
    MaterialModule,
    BrowserAnimationsModule
  ]
})
export class LogModule { }
