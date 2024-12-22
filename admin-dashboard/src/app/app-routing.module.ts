import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import { LoginComponent } from './pages/login/login.component';
import { LayoutComponent } from './components/layout/layout.component';
import { DashboardComponent } from './pages/dashboard/dashboard.component';
import { AddTrainComponent } from './pages/add-train/add-train.component';
import { ViewTrainComponent } from './pages/view-train/view-train.component';
import { AuthGuard } from './auth/auth.guard';
export const routes: Routes = [
  { path: '', component: LoginComponent }, // Default route (homepage)
  {
    path: 'admin',
    component: LayoutComponent,
    canActivate: [AuthGuard], // Protect layout and its children
    children: [
      { path: 'dashboard', component: DashboardComponent },
      { path: 'add-train', component: AddTrainComponent },
      { path: 'view-train', component: ViewTrainComponent },
    ],
  },
  { path: '**', redirectTo: '' }, // Redirect unknown routes to login
];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule { }


