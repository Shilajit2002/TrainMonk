// login.component.ts
import { Component } from '@angular/core';
import { FormsModule } from '@angular/forms'; // Import FormsModule
import { Router } from '@angular/router';
import { HttpClientModule, HttpClient } from '@angular/common/http'; // Import HttpClientModule
import { API_BASE_URL } from '../../helper/baseUrl';

@Component({
  selector: 'app-login',
  standalone: true,
  imports: [FormsModule, HttpClientModule], // Add HttpClientModule here
  templateUrl: './login.component.html',
  styleUrls: ['./login.component.css'],
})
export class LoginComponent {
  email = '';
  password = '';
  role = 'admin';

  constructor(private http: HttpClient, private router: Router) {}

  login() {
    const payload = {
      email: this.email,
      password: this.password,
      role: this.role,
    };

    this.http.post(`${API_BASE_URL}/auth/login`, payload).subscribe({
      next: (response: any) => {
        // Save data to localStorage
        localStorage.setItem('adminId', response.adminId);
        localStorage.setItem('token', response.token);
        localStorage.setItem('role', response.role);

        // Redirect to dashboard
        this.router.navigate(['/admin/dashboard']);
      },
      error: (error) => {
        console.error('Login failed:', error);
        alert('Invalid credentials. Please try again.');
      },
    });
  }
}
