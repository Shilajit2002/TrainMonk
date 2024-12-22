import { Component, OnInit } from '@angular/core';
import { HttpClient, HttpClientModule } from '@angular/common/http';
import { API_BASE_URL } from '../../helper/baseUrl';
import { NgFor } from '@angular/common';

@Component({
  selector: 'app-dashboard',
  standalone: true,
  imports: [HttpClientModule,NgFor],
  templateUrl: './dashboard.component.html',
  styleUrl: './dashboard.component.css',
})
export class DashboardComponent implements OnInit {
  trainCount: number = 0;
  userCount: number = 0;
  trainDetails: {
    trainNumber: string;
    trainName: string;
    numberOfBookedUsers: number;
  }[] = [];

  constructor(private http: HttpClient) {}

  ngOnInit(): void {
    this.fetchDashboardData();
  }

  fetchDashboardData(): void {
    const token = localStorage.getItem('token'); // Retrieve the token from localStorage

    // Check if token exists before making the request
    if (token) {
      // Fetch Train Count
      this.http
        .get(`${API_BASE_URL}/admins/trains/count`, {
          headers: {
            Authorization: `${token}`,
          },
        })
        .subscribe({
          next: (response: any) => {
            this.trainCount = response?.trainCount;
          },
          error: (err) => console.error('Error fetching train count:', err),
        });

      // Fetch Active User Count
      this.http
        .get(`${API_BASE_URL}/admins/users/active/count`, {
          headers: {
            Authorization: `${token}`,
          },
        })
        .subscribe({
          next: (response: any) => {
            this.userCount = response?.userCount;
          },
          error: (err) =>
            console.error('Error fetching active user count:', err),
        });

      // Fetch Train Details
      this.http
        .get(`${API_BASE_URL}/admins/trains/users/booked`, {
          headers: {
            Authorization: `${token}`,
          },
        })
        .subscribe({
          next: (response: any) => {
            this.trainDetails = response?.trainDetails;
            console.log(response?.trainDetails);
            
          },
          error: (err) => console.error('Error fetching train details:', err),
        });
    } else {
      console.error('No token found in localStorage');
    }
  }
}
