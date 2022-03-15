import { Injectable } from '@angular/core';
import { HttpClient, HttpHeaders } from '@angular/common/http';

import { Observable, of } from 'rxjs';
import { catchError, map, tap } from 'rxjs/operators';

const URLPrefix = "http://localhost:8080";

@Injectable({
  providedIn: 'root'
})
export class SecurityService {
  private url = URLPrefix + '/resource';  // URL to web api

  httpOptions = {
    headers: new HttpHeaders({ 'Content-Type': 'application/json' })
    .append('Authorization', 'Basic dXNlcjphM2YyNjQ5NC01NjcwLTQ5ODItOWQ2YS0yMGQ1OWE1ZjNjOWE=')
  };

  constructor(private http: HttpClient) { }

    /** GET COVID19 data from the server */
    getData(): Observable<String[]> {
      return this.http.get(this.url)
        .pipe(
          tap(_ => console.log('fetched data')),
          map(data => data as any),
          catchError(this.handleError('getData', []))
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
