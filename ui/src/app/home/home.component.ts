import { Component, OnInit } from '@angular/core';
import { AppService } from '../app.service';
import { HttpClient } from '@angular/common/http';
import { Greeting } from '../models/Greeting';

@Component({
  templateUrl: './home.component.html',
  selector: 'home-component',
  // somehow this css is not working properly
  // styleUrls: ['./home.component.css']
})
export class HomeComponent {

  title = 'Demo';
  greeting = {} as Greeting;

  constructor(private app: AppService, private http: HttpClient) {
    http.get<Greeting>('http://localhost:8080/resource').subscribe(data => this.greeting = data);
  }

  authenticated() { return this.app.authenticated; }

}
