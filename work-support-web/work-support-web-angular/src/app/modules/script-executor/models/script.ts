import { Option } from './option';

export class Script {

    name: string;

    options: Option[];

    monitoring: boolean;
    
    token: string;
    
    executed: boolean = false;

}
