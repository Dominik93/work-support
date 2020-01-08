import { Component, OnInit } from '@angular/core';
import { ChangeContextRequest } from 'src/app/modules/context/models/change-context-request';
import { Context } from 'src/app/modules/context/models/context';
import { ContextService } from 'src/app/modules/context/services/context.service';
import { ContextHolderService } from 'src/app/modules/context/services/context-holder.service';
import { ConfigService } from 'src/app/modules/context/services/config-service';

@Component({
    selector: 'app-tenant',
    templateUrl: './tenant.component.html',
    styleUrls: ['./tenant.component.css']
})
export class TenantComponent implements OnInit {

    contextual: boolean;

    environments: string[];
    selectedEnvironment: string;

    databases: string[] = [];
    selectedDatabase: string;

    showDatabase: boolean;

    constructor(private contextService: ContextService,
        private configService: ConfigService,
        private contextHolder: ContextHolderService) { }

    ngOnInit() {
        // this.initContext(this.contextHolder.currentContext);
        this.contextHolder.getContextual().subscribe(contextual => {
            this.contextual = contextual;
        })
        this.contextHolder.getContext().subscribe(data => {
            this.initContext(data);
        });
    }

    initContext(context: Context) {
        this.selectedEnvironment = context.environment;
        this.environments = this.configService.config.environments;
        this.databases = context.databases;
        this.selectedDatabase = context.database;
        this.showDatabase = (this.contextual && this.selectedDatabase != null) || this.databases.length > 1;
    }

    onEnvironmentChange() {
        this.changeContext();
    }

    onDatabaseChange() {
        this.changeContext();
    }

    changeContext() {
        var request = new ChangeContextRequest();
        request.database = this.selectedDatabase;
        request.environment = this.selectedEnvironment;
        this.contextService.changeContext(request).subscribe(data => {
            var context = new Context();
            context.database = this.selectedDatabase;
            context.environment = this.selectedEnvironment;
            context.databases = this.contextHolder.currentContext.databases;
            context.actions = data.actions;
            context.moduleActions = data.moduleActions;
            this.contextHolder.setContext(context);
        });
    }

}
