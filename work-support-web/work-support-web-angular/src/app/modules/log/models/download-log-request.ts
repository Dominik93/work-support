import { LogQuery } from "./log-query";

export class DownloadLogRequest {

    applications: string[];

    date;

    from;

    to;

    logQuery: LogQuery = new LogQuery;

    current: boolean;


}