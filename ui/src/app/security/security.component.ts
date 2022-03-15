import { Component, OnInit } from '@angular/core';
// Spring boot Angular
import { HttpClient } from '@angular/common/http';
// Angular material component
import { FormControl, Validators } from '@angular/forms';

@Component({
  selector: 'app-security',
  templateUrl: './security.component.html',
  styleUrls: ['./security.component.css'],
})
export class SecurityComponent implements OnInit {
  // Spring-boot Angular https://github.com/dsyer/spring-boot-angular
  title = 'COVID-19 Monitor';
  data = {'id': 'XXX', 'content': 'Hello World'} as any;
  constructor(private http: HttpClient) {
    http.get('/resource').subscribe((data) => (this.data = data));
  }

  // Angular Component: Form field with prefix & suffix
  hide = true;

  // generated
  ngOnInit(): void {}

  // Angular Component: Form field with error messages
  email = new FormControl('', [Validators.required, Validators.email]);

  getErrorMessage() {
    if (this.email.hasError('required')) {
      return 'You must enter a value';
    }

    return this.email.hasError('email') ? 'Not a valid email' : '';
  }
}
