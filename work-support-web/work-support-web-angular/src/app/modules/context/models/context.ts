import { ModuleActions } from './module-actions';
import { Actions } from './actions';


export class Context {

    database: string;

    environment: string;

    databases: string[];

    moduleActions: ModuleActions;

    actions: Actions;
}
