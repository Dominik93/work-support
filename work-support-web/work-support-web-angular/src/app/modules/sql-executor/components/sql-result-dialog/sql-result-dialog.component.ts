import { Component, OnInit, Inject } from '@angular/core';
import { MatDialogRef, MAT_DIALOG_DATA, MatTableDataSource } from '@angular/material';
import { DialogSqlResultData } from '../../models/dialog-sql-result-data';
import { Row } from '../../models/row';

@Component({
  selector: 'app-sql-result-dialog',
  templateUrl: './sql-result-dialog.component.html',
  styleUrls: ['./sql-result-dialog.component.css']
})
export class SqlResultDialogComponent {

  rows: Row[];
  headers: string[]
  dataSource;

  constructor(
    public dialogRef: MatDialogRef<SqlResultDialogComponent>,
    @Inject(MAT_DIALOG_DATA) public data: DialogSqlResultData) {
    this.rows = data.rows;
    this.headers = data.headers;
    this.dataSource = new MatTableDataSource(this.rows);
  }

  onNoClick(): void {
    this.dialogRef.close();
  }

  getCell(row, column) {
    var index = this.headers.indexOf(column);
    var r = row.values[index];
    var cell = " " + r;
    return cell;
  }

  applyFilter(filterValue: string) {
    this.dataSource.filter = filterValue.trim().toLowerCase();
  }


}
