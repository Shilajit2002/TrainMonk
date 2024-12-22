// app.module.ts
import { NgModule } from '@angular/core';
import { BrowserModule } from '@angular/platform-browser';
import { AppRoutingModule } from './app-routing.module';
import { HttpClientModule } from '@angular/common/http'; // Import HttpClientModule
import { LoginComponent } from './pages/login/login.component';
import { LayoutComponent } from './components/layout/layout.component';
import { DashboardComponent } from './pages/dashboard/dashboard.component';
import { AddTrainComponent } from './pages/add-train/add-train.component';
import { ViewTrainComponent } from './pages/view-train/view-train.component';
import { CommonModule } from '@angular/common';

@NgModule({
  declarations: [],
  imports: [
    BrowserModule,
    AppRoutingModule,
    HttpClientModule, // Ensure HttpClientModule is imported
    LoginComponent,
    LayoutComponent,
    DashboardComponent,
    AddTrainComponent,
    ViewTrainComponent,
    CommonModule
  ],
  providers: [],
  bootstrap: [],
})
export class AppModule {}
