import { Component, OnInit } from '@angular/core';
import { ResultService } from '../../../core/services/result/result.service';

@Component({
  selector: 'app-result-snackbar',
  templateUrl: './result-snackbar.component.html',
  styleUrls: ['./result-snackbar.component.css']
})
export class ResultSnackbarComponent implements OnInit {

  message: string;

  constructor(private resultService: ResultService) { }

  ngOnInit() {
    var result = this.resultService.curretResult;
    this.message = "Fail";
    if (result) {
      this.message = "Success";
    }
  }

}
