import { Component, OnInit, AfterViewInit, OnDestroy } from '@angular/core';
import { LogDownloaderService } from '../../services/log-downloader.service';
import { FormControl } from '@angular/forms';
import { Subscription } from 'rxjs';
import { ApplicationGroup } from '../../models/application-group';
import { ContextHolderService } from 'src/app/modules/context/services/context-holder.service';
import { ConfigService } from 'src/app/modules/context/services/config-service';
import { Router } from '@angular/router';

@Component({
  selector: 'app-log-root',
  templateUrl: './log-root.component.html',
  styleUrls: ['./log-root.component.css']
})
export class LogRootComponent implements OnInit, AfterViewInit, OnDestroy {

  times = [];
  fromTime: string;
  toTime: string;
  applications = new FormControl();
  applicationGroups: ApplicationGroup[] = [];
  apps: string[];
  current: boolean;
  contextSubscription: Subscription;

  date = new FormControl(new Date());

  constructor(private logDownloaderService: LogDownloaderService,
    private configService: ConfigService,
    private router: Router,
    private contextService: ContextHolderService) {
    this.current = false;
    this.date = new FormControl(new Date());
    for (var i = 1; i < 25; i++) {
      this.times.push(i + ":00");
    }
    this.fromTime = "6:00";
    this.toTime = "6:00";
  }

  ngOnInit() {
    this.contextService.setContextDatabase(this.configService.config.log);
    this.initApplications()
    this.contextSubscription = this.contextService.getContext().subscribe(data => {
      if (data.moduleActions.log) {
        this.initApplications();
      } else {
        this.router.navigateByUrl('');
      }
    });
  }

  initApplications() {
    this.logDownloaderService.getApplications().subscribe(data => {
      this.applicationGroups = data.groups;
    });
  }

  ngOnDestroy() {
    this.contextSubscription.unsubscribe();
  }

  ngAfterViewInit() {
    this.contextService.setContextual(true);
  }

  applicationChange(event) {
    this.apps = event.value;
  }

  fromTimeChange(event) {
    this.toTime = event.value;
  }

}
