import { Injectable } from '@angular/core';
import { HttpClient, HttpHeaders } from '@angular/common/http';
import { Credentials } from './models/Credentials';
import { User } from './models/User';

@Injectable()
export class AppService {

  authenticated = false;

  constructor(private http: HttpClient) {
  }

  authenticate(credentials: Credentials, callback: () => void) {

        const headers = new HttpHeaders(credentials ? {
            Authorization : 'Basic ' + btoa(credentials.username + ':' + credentials.password)
        } : {});

        this.http.get<User>('http://localhost:8080/user', {headers: headers}).subscribe(response => {
            if (response.name) {
                this.authenticated = true;
            } else {
                this.authenticated = false;
            }
            return callback && callback();
        });

    }

}
