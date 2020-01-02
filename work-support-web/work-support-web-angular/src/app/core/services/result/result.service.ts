import { Injectable } from '@angular/core';
import { Subject } from 'rxjs';

@Injectable({
  providedIn: 'root'
})
export class ResultService {

  private resultSnackBarSubject = new Subject<Boolean>();

  private resultDialogSubject = new Subject<Boolean>();

  resultSnackBarState = this.resultSnackBarSubject.asObservable();

  
  resultDialogState = this.resultDialogSubject.asObservable();


  curretResult: any;

  constructor() { }

  showSnackBar(result: any) {
    console.log("showSnackBar " + result);
    this.curretResult = result;
    this.resultSnackBarSubject.next(true);
  }

  hideSnackBar() {
    console.log("hideSnackBar");
    this.resultSnackBarSubject.next(false);
  }

  showDialog(result: any) {  
    console.log("showDialog " + result);
    this.resultDialogSubject.next(true);
  }

  hideDialog() {
    console.log("hideDialog");
    this.resultDialogSubject.next(false);
    
  }

}
