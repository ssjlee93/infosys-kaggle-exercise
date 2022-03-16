import { AfterViewInit, Component, ViewChild } from '@angular/core';
import { Datum } from '../models/Datum';

import { MatPaginator } from '@angular/material/paginator';
import { MatSort } from '@angular/material/sort';
import { MatTable, MatTableDataSource } from '@angular/material/table';
import { Covid19Service } from '../covid19/covid19.service';

/**
 * @title Data table with sorting, pagination, and filtering.
 */
@Component({
  selector: 'app-table',
  templateUrl: './table.component.html',
  styleUrls: ['./table.component.css'],
})
export class TableComponent {

  @ViewChild(MatPaginator) paginator!: MatPaginator;
  @ViewChild(MatSort) sort!: MatSort;
  @ViewChild(MatTable) table!: MatTable<Datum>;
  dataSource: MatTableDataSource<Datum>;

  /** Columns displayed in the table. Columns IDs can be added, removed, or reordered. */
  displayedColumns: string[] = [
    'Country/Region',
    'Province/State',
    'City',
    'Confirmed',
    'Deaths',
    "Recovered",
    "Active"
  ];

  constructor(private covid19Service: Covid19Service) {
    this.dataSource = new MatTableDataSource<Datum>();
  }

  // on initialize, load data via API
   ngOnInit(): void {
    this.showData();
  }

  // HTTP GET all data and subscribe
  showData() {
    this.covid19Service.getData().subscribe((data) => (this.dataSource.data = data));
  }

  ngAfterViewInit() {
    this.dataSource.paginator = this.paginator;
    this.dataSource.sort = this.sort;
    this.table.dataSource = this.dataSource;
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


}
