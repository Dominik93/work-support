import { Script } from './script';


export class GetScriptsResponse {

    scripts: Script[];

    constructor(scripts: Script[]){
        this.scripts = scripts;
    }

}
