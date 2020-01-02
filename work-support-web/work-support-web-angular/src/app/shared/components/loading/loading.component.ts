import { Component, OnInit, ChangeDetectorRef, OnDestroy } from '@angular/core';
import { Subscription } from 'rxjs';
import { LoaderService } from '../../../core/services/loader/loader.service';
import { MatDialog } from '@angular/material';
import { LoadingDialogComponent } from '../loading-dialog/loading-dialog.component';

@Component({
  selector: 'app-loading',
  templateUrl: './loading.component.html',
  styleUrls: ['./loading.component.css']
})
export class LoadingComponent implements OnInit, OnDestroy {
  private subscription: Subscription;

  constructor(private loadingService: LoaderService,
    public dialog: MatDialog) { }

  private currentState: Boolean = false;

  ngOnInit() {
    this.subscription = this.loadingService.loadingState.subscribe(data => {
      if (data && !this.currentState) {
        console.log("showLoading");
        this.openDialog();
      } else if (!data && this.currentState) {
        console.log("closeLoading");
        this.closeDialog();
      }
      this.currentState = data;
    });
  }

  ngOnDestroy() {
    this.subscription.unsubscribe();
  }


  closeDialog(): void {
    this.dialog.getDialogById("loading_dialog").close()
  }

  openDialog(): void {
    this.dialog.open(LoadingDialogComponent, {
      id: "loading_dialog",
      width: '150px'
    });
  }

}
