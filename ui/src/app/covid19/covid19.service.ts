import { Injectable } from '@angular/core';
import { HttpClient, HttpHeaders } from '@angular/common/http';

import { Observable, of } from 'rxjs';
import { catchError, map, tap } from 'rxjs/operators';

import { Datum } from '../models/Datum';
import { MatTableDataSource } from '@angular/material/table';
import { USDatum } from '../models/USDatum';


const URLPrefix = "http://localhost:8080";

@Injectable({
  providedIn: 'root'
})
export class Covid19Service {

  httpOptions = {
    headers: new HttpHeaders({ 'Content-Type': 'application/json' })
    .append("Authorization", "Basic dXNlcjphM2YyNjQ5NC01NjcwLTQ5ODItOWQ2YS0yMGQ1OWE1ZjNjOWE=")
  };

  constructor(private http: HttpClient) {   }

    /** GET COVID19 world data from the server */
    getData(): Observable<Datum[]> {
      return this.http.get<Datum[]>(URLPrefix + '/world')
        .pipe(
          tap(_ => console.log('fetched data')),
          map(data => data as Datum[]),
          catchError(this.handleError<Datum[]>('getData', []))
        );
    }

        /** GET COVID19 US data from the server */
        getUsData(): Observable<USDatum[]> {
          return this.http.get<USDatum[]>(URLPrefix + '/us')
            .pipe(
              tap(_ => console.log('fetched data')),
              map(data => data as USDatum[]),
              catchError(this.handleError<USDatum[]>('getData', []))
            );
        }

  /**
   * Handle Http operation that failed.
   * Let the app continue.
   *
   * @param operation - name of the operation that failed
   * @param result - optional value to return as the observable result
   */
  private handleError<T>(operation = 'operation', result?: T) {
    return (error: any): Observable<T> => {

      // TODO: send the error to remote logging infrastructure
      console.error(error); // log to console instead

      // TODO: better job of transforming error for user consumption
      console.log(`${operation} failed: ${error.message}`);

      // Let the app keep running by returning an empty result.
      return of(result as T);
    };
  }
}
