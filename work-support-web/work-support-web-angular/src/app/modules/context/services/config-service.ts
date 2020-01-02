import { Injectable } from '@angular/core';
import { Config } from '../models/config';

@Injectable({
  providedIn: 'root'
})
export class ConfigService {
   
  config: Config;
  
  constructor() {
    console.log("Store constructor");
  }

}
