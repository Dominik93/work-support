import { ModuleConfig } from "./module-config";

export class Config {

    environments : string[] = [];

    defaultEnvironment: string;

    logDownloader: ModuleConfig;

    sqlExecutor: ModuleConfig;
    
    scriptExecutor: ModuleConfig;

}
