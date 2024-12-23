// auth.service.ts
import { Injectable } from '@angular/core';
import { Router } from '@angular/router';

@Injectable({
  providedIn: 'root',
})
export class AuthService {
  constructor(private router: Router) {}

  // Check if user is authenticated by checking localStorage
  isAuthenticated(): boolean {
    const token = localStorage.getItem('token');
    const adminId = localStorage.getItem('userId');
    return !!token && !!adminId;
  }

  // Log the user out (clear localStorage)
  logout(): void {
    localStorage.removeItem('token');
    localStorage.removeItem('userId');
    this.router.navigate(['/register']);
  }
}
