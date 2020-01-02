import { Injectable } from '@angular/core';
import { Subject } from 'rxjs';

@Injectable({
  providedIn: 'root'
})
export class ErrorService {

  private errorSubject = new Subject<Boolean>();

  errorState = this.errorSubject.asObservable();

  currentError: any;

  constructor() { }

  show(error: any) {
    this.currentError = error;
    this.errorSubject.next(true);
  }

  hide() {
    this.errorSubject.next(false);
  }
}
