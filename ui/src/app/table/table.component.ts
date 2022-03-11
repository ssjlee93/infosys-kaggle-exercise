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
  displayedColumns: string[] = ['Country/Region', 'Province/State', 'City',  'Confirmed', 'Deaths'];
  dataSource: MatTableDataSource<Datum> = new MatTableDataSource<Datum>([]);

  @ViewChild(MatPaginator) paginator!: MatPaginator;
  @ViewChild(MatSort) sort!: MatSort;
  constructor(private covid19Service: Covid19Service) {}
  
  ngOnInit(): void {
    this.showData();
  }

  ngAfterViewInit() {
    this.dataSource.paginator = this.paginator;
    this.dataSource.sort = this.sort;
    this.dataSource.filterPredicate = (data: Datum, filter: string) => {
      return data.countryRegion.indexOf(filter) > -1;
     };
  }

  applyFilter(event: Event) {
    const filterValue = (event.target as HTMLInputElement).value;
    this.dataSource.filter = filterValue.trim().toLowerCase();

    this.dataSource.filter = filterValue;

    if (this.dataSource.paginator) {
      this.dataSource.paginator.firstPage();
    }
  }

  // HTTP GET all data and subscribe
  showData() {
    this.covid19Service.getData()
    .subscribe(data => this.dataSource.data=data);
  }
}


