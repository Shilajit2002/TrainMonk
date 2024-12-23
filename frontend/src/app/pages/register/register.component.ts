import { Component, OnInit } from '@angular/core';
import { HttpClient, HttpClientModule } from '@angular/common/http';
import {
  FormBuilder,
  FormGroup,
  FormsModule,
  ReactiveFormsModule,
  Validators,
} from '@angular/forms';
import { API_BASE_URL } from '../../helper/baseUrl';
import { NgFor, NgIf } from '@angular/common';
import Swal from 'sweetalert2';

@Component({
  selector: 'app-register',
  standalone: true,
  imports: [FormsModule, HttpClientModule, ReactiveFormsModule, NgFor, NgIf],
  templateUrl: './register.component.html',
  styleUrl: './register.component.css',
})
export class RegisterComponent implements OnInit {
  registerForm: FormGroup;
  loginForm: FormGroup;
  modalVisible = false;
  isLoginModalVisible = false;

  fields = [
    {
      label: 'Username',
      name: 'username',
      id: 'username',
      type: 'text',
      placeholder: 'Enter your username',
      icon: 'M7.5 6a4.5 4.5 0 1 1 9 0 4.5 4.5 0 0 1-9 0ZM3.751 20.105a8.25 8.25 0 0 1 16.498 0 .75.75 0 0 1-.437.695A18.683 18.683 0 0 1 12 22.5c-2.786 0-5.433-.608-7.812-1.7a.75.75 0 0 1-.437-.695Z',
      required: true,
      errorMessage: 'Username is required',
    },
    {
      label: 'Password',
      name: 'password',
      id: 'password',
      type: 'password',
      placeholder: 'Enter your password',
      icon: 'M12 1.5a5.25 5.25 0 0 0-5.25 5.25v3a3 3 0 0 0-3 3v6.75a3 3 0 0 0 3 3h10.5a3 3 0 0 0 3-3v-6.75a3 3 0 0 0-3-3v-3c0-2.9-2.35-5.25-5.25-5.25Zm3.75 8.25v-3a3.75 3.75 0 1 0-7.5 0v3h7.5Z',
      required: true,
      errorMessage: 'Password is required',
    },
    {
      label: 'Confirm Password',
      name: 'confirmPassword',
      id: 'confirmPassword',
      type: 'password',
      placeholder: 'Confirm your password',
      icon: 'M12 1.5a5.25 5.25 0 0 0-5.25 5.25v3a3 3 0 0 0-3 3v6.75a3 3 0 0 0 3 3h10.5a3 3 0 0 0 3-3v-6.75a3 3 0 0 0-3-3v-3c0-2.9-2.35-5.25-5.25-5.25Zm3.75 8.25v-3a3.75 3.75 0 1 0-7.5 0v3h7.5Z',
      required: true,
      errorMessage: 'Confirm password is required',
    },
    {
      label: 'Email',
      name: 'email',
      id: 'email',
      type: 'email',
      placeholder: 'Enter your email',
      icon: 'M1.5 8.67v8.58a3 3 0 0 0 3 3h15a3 3 0 0 0 3-3V8.67l-8.928 5.493a3 3 0 0 1-3.144 0L1.5 8.67Z M22.5 6.908V6.75a3 3 0 0 0-3-3h-15a3 3 0 0 0-3 3v.158l9.714 5.978a1.5 1.5 0 0 0 1.572 0L22.5 6.908Z',
      required: true,
      errorMessage: 'Email is required',
    },
    {
      label: 'Contact Number',
      name: 'contactNumber',
      id: 'contactNumber',
      type: 'text',
      placeholder: 'Enter your contact number',
      icon: 'M1.5 4.5a3 3 0 0 1 3-3h1.372c.86 0 1.61.586 1.819 1.42l1.105 4.423a1.875 1.875 0 0 1-.694 1.955l-1.293.97c-.135.101-.164.249-.126.352a11.285 11.285 0 0 0 6.697 6.697c.103.038.25.009.352-.126l.97-1.293a1.875 1.875 0 0 1 1.955-.694l4.423 1.105c.834.209 1.42.959 1.42 1.82V19.5a3 3 0 0 1-3 3h-2.25C8.552 22.5 1.5 15.448 1.5 6.75V4.5Z',
      required: true,
      errorMessage: 'Contact number is required',
    },
    {
      label: 'Address',
      name: 'address',
      id: 'address',
      type: 'text',
      placeholder: 'Enter your address',
      icon: 'M11.47 3.841a.75.75 0 0 1 1.06 0l8.69 8.69a.75.75 0 1 0 1.06-1.061l-8.689-8.69a2.25 2.25 0 0 0-3.182 0l-8.69 8.69a.75.75 0 1 0 1.061 1.06l8.69-8.689Z m12 5.432 8.159 8.159c.03.03.06.058.091.086v6.198c0 1.035-.84 1.875-1.875 1.875H15a.75.75 0 0 1-.75-.75v-4.5a.75.75 0 0 0-.75-.75h-3a.75.75 0 0 0-.75.75V21a.75.75 0 0 1-.75.75H5.625a1.875 1.875 0 0 1-1.875-1.875v-6.198a2.29 2.29 0 0 0 .091-.086L12 5.432Z',
      required: true,
      errorMessage: 'Address is required',
    },
  ];

  constructor(private fb: FormBuilder, private http: HttpClient) {
    // Initialize form groups in the constructor
    this.registerForm = this.fb.group({
      username: ['', Validators.required],
      password: ['', Validators.required],
      confirmPassword: ['', Validators.required],
      email: ['', Validators.required],
      contactNumber: ['', Validators.required],
      address: ['', Validators.required],
    });

    this.loginForm = this.fb.group({
      email: ['', Validators.required],
      password: ['', Validators.required],
      role: 'customer',
    });
  }

  ngOnInit(): void {}

  onRegisterSubmit() {
    if (this.registerForm.valid) {
      const user = {
        userName: this.registerForm.value.username,
        email: this.registerForm.value.email,
        password: this.registerForm.value.password,
        contactNumber: this.registerForm.value.contactNumber,
        address: this.registerForm.value.address,
      };

      this.http.post(`${API_BASE_URL}/users/register`, user).subscribe({
        next: (response: any) => {
          console.log('Registration Success:', response);
          this.modalVisible = true;
          this.registerForm.reset();
          Swal.fire({
            icon: 'success',
            title: response?.message,
          });
        },
        error: (error) => {
          console.error('Error:', error);
          alert('Registration failed, please try again.');
        },
      });
    } else {
      alert('Please fill all required fields correctly.');
    }
  }

  onLoginSubmit() {
    if (this.loginForm.valid) {
      const loginData = {
        email: this.loginForm.value.email,
        password: this.loginForm.value.password,
        role: 'customer',
      };

      this.http.post(`${API_BASE_URL}/auth/login`, loginData).subscribe({
        next: (response: any) => {
          console.log('Login Success:', response);
          // Handle successful login logic here, such as storing token or redirecting
          localStorage.setItem('userId', response.userId);
          localStorage.setItem('token', response.token);
          localStorage.setItem('role', response.role);

          this.loginForm.reset();
          this.toggleLogin();
        },
        error: (error) => {
          console.error('Error:', error);
          alert('Login failed, please check your credentials.');
        },
      });
    } else {
      alert('Please fill in all fields.');
    }
  }

  // Toggle Login Modal
  toggleLogin() {
    this.isLoginModalVisible = !this.isLoginModalVisible;
  }

  // Close Registration Modal
  closeModal() {
    this.modalVisible = false;
  }

  // Close Login Modal
  closeLoginModal() {
    this.isLoginModalVisible = false;
  }
}
