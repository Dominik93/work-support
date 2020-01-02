import { ModuleConfig } from "./module-config";

export class Config {

    environments : string[] = [];

    defaultEnvironment: string;

    log: ModuleConfig;

    sqlExecutor: ModuleConfig;
    
    scriptExecutor: ModuleConfig;

}
