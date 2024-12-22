import { Component } from '@angular/core';
import { FormsModule } from '@angular/forms';
import { Router } from '@angular/router';
import { HttpClientModule, HttpClient } from '@angular/common/http'; // Import HttpClientModule
import { API_BASE_URL } from '../../helper/baseUrl';
import Swal from 'sweetalert2';

@Component({
  selector: 'app-add-train',
  imports: [FormsModule, HttpClientModule],
  templateUrl: './add-train.component.html',
  styleUrl: './add-train.component.css',
})
export class AddTrainComponent {
  trainData = {
    trainNumber: '',
    trainName: '',
    originStation: '',
    destinationStation: '',
    departureTime: '',
    arrivalTime: '',
    date: '',
    numberOfSeats: 0,
    tierClassPrice: {
      'AC First Class': 0,
      Sleeper: 0,
      General: 0,
    },
  };

  constructor(private http: HttpClient, private router: Router) {}

  onSubmit() {
    const token = localStorage.getItem('token');
    if (!token) {
      Swal.fire('Error', 'Unauthorized. Please login again!');
      return;
    }

    this.http
      .post(`${API_BASE_URL}/admins/trains/add`, this.trainData, {
        headers: {
          Authorization: `${token}`,
        },
      })
      .subscribe({
        next: (response: any) => {
          this.trainData = {
            // Reset form after success
            trainNumber: '',
            trainName: '',
            originStation: '',
            destinationStation: '',
            departureTime: '',
            arrivalTime: '',
            date: '',
            numberOfSeats: 0,
            tierClassPrice: {
              'AC First Class': 0,
              Sleeper: 0,
              General: 0,
            },
          };

          Swal.fire('success', response?.message);
        },
        error: (error) => {
          console.error('Error:', error);
          Swal.fire('error', 'There was a problem adding the train!');
        },
      });
  }
}
