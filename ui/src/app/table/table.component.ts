import {AfterViewInit, Component, ViewChild} from '@angular/core';
import { Datum } from '../covid19/Datum';

import {MatPaginator} from '@angular/material/paginator';
import {MatSort} from '@angular/material/sort';
import {MatTableDataSource} from '@angular/material/table';
import { Covid19Service } from '../covid19/covid19.service';

/**
 * @title Data table with sorting, pagination, and filtering.
 */
 @Component({
  selector: 'app-table',
  templateUrl: './table.component.html',
  styleUrls: ['./table.component.css']
})
export class TableComponent {
  displayedColumns: string[] = ['City', 'Province/State', 'Country/Region', 'Confirmed', 'Deaths'];
  dataSource: Datum[] = [];

  constructor(private covid19Service: Covid19Service) {}
  
  ngOnInit(): void {
    this.showData();
  }

  // HTTP GET all data and subscribe
  showData() {
    this.covid19Service.getData()
    .subscribe(data => this.dataSource=data);
  }
}


