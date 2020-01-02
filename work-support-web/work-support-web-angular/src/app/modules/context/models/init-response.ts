import { Actions } from "./actions";
import { ModuleActions } from "./module-actions";
import { Config } from "./config";

export class InitResponse {

    moduleActions: ModuleActions;

    actions: Actions;
   
    config: Config;

}
