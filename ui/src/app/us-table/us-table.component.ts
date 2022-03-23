import { AfterViewInit, Component, ViewChild } from '@angular/core';
import { MatPaginator } from '@angular/material/paginator';
import { MatSort } from '@angular/material/sort';
import { MatTable, MatTableDataSource } from '@angular/material/table';
import { Covid19Service } from '../covid19/covid19.service';
import { USDatum } from '../models/USDatum';
import { UsTableDataSource, UsTableItem } from './us-table-datasource';

@Component({
  selector: 'app-us-table',
  templateUrl: './us-table.component.html',
  styleUrls: ['./us-table.component.css']
})
export class UsTableComponent implements AfterViewInit {
  @ViewChild(MatPaginator) paginator!: MatPaginator;
  @ViewChild(MatSort) sort!: MatSort;
  @ViewChild(MatTable) table!: MatTable<USDatum>;
  dataSource: MatTableDataSource<USDatum>;

  /** Columns displayed in the table. Columns IDs can be added, removed, or reordered. */
  displayedColumns: string[] = [
    "Id",
    'Country/Region',
    'Province/State',
    'Confirmed',
    'Deaths',
    "Recovered",
    "Active"
  ];

  constructor(private covid19Service: Covid19Service) {
    this.dataSource = new MatTableDataSource<USDatum>();
  }
  // on initialize, load data via API
  ngOnInit(): void {
    this.showData();
  }

    // HTTP GET all data and subscribe
    showData() {
      this.covid19Service.getUsData().subscribe((data) => (this.dataSource.data = data));
    }
  ngAfterViewInit(): void {
    this.dataSource.sort = this.sort;
    this.dataSource.paginator = this.paginator;
    this.table.dataSource = this.dataSource;
    this.dataSource.filterPredicate = (data: USDatum, filter: string) => {
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
