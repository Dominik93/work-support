import { LogQuery } from "./log-query";

export class InitLiveLogRequest {

    applications: string[];

    logQuery: LogQuery = new LogQuery;

}