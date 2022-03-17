import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
// components
import { TableComponent } from './table/table.component';
import { HomeComponent } from './home/home.component';
import { LoginComponent } from './login/login.component';
import { SecurityComponent } from './security/security.component';
import { DashboardComponent } from './dashboard/dashboard.component';

const routes: Routes = [
  { path: '',  pathMatch: 'full', redirectTo: 'home'},
  { path: 'home', component: HomeComponent},
  { path: 'login', component: LoginComponent},
  { path: 'table', component: TableComponent },
  { path: 'security', component: SecurityComponent },
  { path: 'dashboard', component: DashboardComponent },
];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule { }
