import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import { AuthGuard } from './auth/auth.guard';
import { RegisterComponent } from './pages/register/register.component';
import { HomeComponent } from './pages/home/home.component';
import { SearchResultsComponent } from './pages/search-results/search-results.component';
export const routes: Routes = [
  { path: '', component: HomeComponent }, // Default route (homepage)
  { path: 'register', component: RegisterComponent }, // Default route (homepage)
  { path: 'search-results', component: SearchResultsComponent }, // Default route (homepage)
  // {
  //   path: 'admin',
  //   component: LayoutComponent,
  //   canActivate: [AuthGuard], // Protect layout and its children
  //   children: [
  //     { path: 'dashboard', component: DashboardComponent },
  //     { path: 'add-train', component: AddTrainComponent },
  //     { path: 'view-train', component: ViewTrainComponent },
  //   ],
  // },
  { path: '**', redirectTo: '' }, // Redirect unknown routes to login
];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule { }


