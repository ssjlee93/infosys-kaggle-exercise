import { Injectable } from '@angular/core';
import { HttpClient, HttpHeaders } from '@angular/common/http';

import { Observable, of } from 'rxjs';
import { catchError, map, tap } from 'rxjs/operators';

import { Datum } from './Datum';


const URLPrefix = "http://localhost:8080";

@Injectable({
  providedIn: 'root'
})
export class Covid19Service {

  private url = URLPrefix + '/data/03-08-2022';  // URL to web api

  httpOptions = {
    headers: new HttpHeaders({ 'Content-Type': 'application/json' })
  };

  constructor(private http: HttpClient) { }

    /** GET COVID19 data from the server */
    getData(): Observable<Datum[]> {
      return this.http.get<Datum[]>(this.url)
        .pipe(
          tap(_ => console.log('fetched data')),
          map(data => data as Datum[]),
          catchError(this.handleError<Datum[]>('getData', []))
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
