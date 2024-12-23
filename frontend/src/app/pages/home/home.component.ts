import { Component, OnInit } from '@angular/core';
import { API_BASE_URL } from '../../helper/baseUrl';
import { HttpClient, HttpClientModule } from '@angular/common/http';
import { FormsModule, ReactiveFormsModule } from '@angular/forms';
import { Router } from '@angular/router';

@Component({
  selector: 'app-home',
  imports: [FormsModule, HttpClientModule, ReactiveFormsModule],
  templateUrl: './home.component.html',
  styleUrl: './home.component.css',
})
export class HomeComponent implements OnInit{
  boardingStation: string = '';
  destinationStation: string = '';
  date: string = '';
  trainClass: string = '';

  constructor(private http: HttpClient, private router: Router) {}

  text: string = "Welcome to TrainMonk – Start Your Journey to Seamless Travel!";
  typedText: string = '';
  index: number = 0;
  isDeleting: boolean = false;

  ngOnInit(): void {
    this.animateText();
  }

  // Function for typing and backspacing effect
  animateText() {
    if (this.isDeleting && this.index >= 0) {
      // Backspacing
      this.typedText = this.text.slice(0, this.index);
      this.index--;
    } else if (!this.isDeleting && this.index <= this.text.length) {
      // Typing
      this.typedText = this.text.slice(0, this.index);
      this.index++;
    }

    if (this.index === this.text.length) {
      // Start deleting after typing is complete
      this.isDeleting = true;
      setTimeout(() => this.animateText(), 1000); // Pause before backspacing
    } else if (this.index === 0) {
      // Start typing again after deleting is complete
      this.isDeleting = false;
      setTimeout(() => this.animateText(), 500); // Pause before typing again
    } else {
      setTimeout(() => this.animateText(), this.isDeleting ? 50 : 100); // Typing/backspacing speed
    }
  }

  onSearch(): void {
    if (!this.boardingStation || !this.destinationStation || !this.date) {
      alert('Please fill all the fields.');
      return;
    }

    // Create the API URL with query parameters
    const apiUrl = `${API_BASE_URL}/users/trains/search?originStation=${encodeURIComponent(
      this.boardingStation
    )}&destinationStation=${encodeURIComponent(
      this.destinationStation
    )}&date=${encodeURIComponent(this.date)}&tierClass=${encodeURIComponent(
      this.trainClass
    )}`;

    // Make the API call to search trains
    this.http.get(apiUrl).subscribe(
      (response: any) => {
        if (response && response.train && response.train.length > 0) {
          // Store the results in local storage or use a service to pass data
          localStorage.setItem('searchResults', JSON.stringify(response.train));
          localStorage.setItem('searchResultsClass', this.trainClass);

          // Redirect to the results page
          this.router.navigate(['/search-results']);
        } else {
          alert('No trains found matching the search criteria.');
        }
      },
      (error: any) => {
        console.error('Error occurred:', error);
        alert('An error occurred while searching for trains.');
      }
    );
  }
}
