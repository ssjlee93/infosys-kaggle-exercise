import { Component } from '@angular/core';
import { AppService } from './app.service';
import { HttpClient } from '@angular/common/http';
import { Router } from '@angular/router';
// Stack overflow: rxjs 6+ replaces "finally" to "finalize"
import { finalize } from 'rxjs/operators';

@Component({
  selector: 'app-root',
  templateUrl: './app.component.html',
  styleUrls: ['./app.component.css']
})
export class AppComponent {
  title = "ui";
  constructor(private app: AppService, private http: HttpClient, private router: Router) {
      // this.app.authenticate(undefined, undefined);
    }
    logout() {
      this.http.post('logout', {})
      // must pipe to finalzie
      .pipe(
      finalize(() => {
          this.app.authenticated = false;
          this.router.navigateByUrl('/login');
      })).subscribe();
    }

}
