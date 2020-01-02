import { NgModule } from '@angular/core';
import { Routes, RouterModule } from '@angular/router';
import { HomeComponent } from './core/components/home/home.component';
import { SqlExecutorComponent } from './modules/sql-executor/components/sql-executor/sql-executor.component';
import { ScriptExecutorComponent } from './modules/script-executor/components/script-executor/script-executor.component';
import { LogRootComponent } from './modules/log/components/log-root/log-root.component';


const routes: Routes = [
  { path: '', component: HomeComponent },
  { path: 'script-executor', component: ScriptExecutorComponent },
  { path: 'log', component: LogRootComponent },
  { path: 'sql-executor', component: SqlExecutorComponent }
];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule { }
