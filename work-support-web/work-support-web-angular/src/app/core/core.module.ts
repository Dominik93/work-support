import { NgModule } from '@angular/core';
import { LoadingDialogComponent } from '../shared/components/loading-dialog/loading-dialog.component';
import { ErrorDialogComponent } from '../shared/components/error-dialog/error-dialog.component';
import { TenantComponent } from './components/tenant/tenant.component';
import { MenuComponent } from './components/menu/menu.component';
import { HomeComponent } from './components/home/home.component';
import { CommonModule } from '@angular/common';
import { FormsModule } from '@angular/forms';
import { MaterialModule } from '../modules/material/material.module';
import { ErrorComponent } from '../shared/components/error/error.component';
import { LoadingComponent } from '../shared/components/loading/loading.component';
import { AppRoutingModule } from '../app-routing.module';
import { ResultComponent } from '../shared/components/result/result.component';
import { ResultSnackbarComponent } from '../shared/components/result-snackbar/result-snackbar.component';
import { PromptDialogComponent } from '../shared/components/prompt-dialog/prompt-dialog.component';


@NgModule({
  declarations: [
    HomeComponent,
    MenuComponent,
    TenantComponent,
    LoadingDialogComponent,
    ErrorDialogComponent,
    ErrorComponent,
    LoadingComponent,
    ResultComponent,
    ResultSnackbarComponent,
    PromptDialogComponent
  ],
  entryComponents: [
    LoadingDialogComponent,
    ResultSnackbarComponent,
    ErrorDialogComponent,
    PromptDialogComponent
  ],
  imports: [
    CommonModule,
    FormsModule,
    AppRoutingModule,
    MaterialModule,
  ],
  exports:[
    MenuComponent,
    HomeComponent,
    TenantComponent,
    LoadingComponent,
    ResultComponent,
    ErrorComponent
  ]
})
export class CoreModule { }
