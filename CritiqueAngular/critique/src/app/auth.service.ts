import { Injectable } from '@angular/core';
import { HttpClient, HttpHeaders } from '@angular/common/http';

import { Observable, throwError } from 'rxjs';
import { catchError, tap } from 'rxjs/operators';

@Injectable({
  providedIn: 'root'
})
export class AuthService {

  constructor(private http: HttpClient) {}

  login(username, password) {
    console.log(username);
    console.log(password);
    // Make Token
    const token = this.generateBasicAuthToken(username, password);
    console.log(token);
    // send token as Authorization header
    const headers = new HttpHeaders().set(
      'Authorization', `Basic ${token}`
    );

    // create request to autenticate creditials
    return this.http.get('http://localhost:8080/authenticate', {headers})
    .pipe(
      tap((res) => {
        console.log(token);
        localStorage.setItem('token', token);
        console.log(res);
        return res;
      }),
      catchError((err: any) => {
        console.log(err);
        return throwError('Error in auth.service.login. Error status: ' + err.status);
      })
      );
    }

    register(user) {
      const httpOptions = {
        headers: new HttpHeaders({
          'Content-Type': 'application/json'
        })
      };
      // create request to register a new account
      console.log(user);
      return this.http.post('http://localhost:8080/register', user, httpOptions)
      .pipe(
        // tap((res) => { // create user and upon success, log them in
        //   this.login(user.username, user.password);

        // }),
        catchError((err: any) => {
          console.log(err);
          return throwError('Error in auth.service.register. Error status: ' + err.status);
      })
    );
  }

  logout() {
    localStorage.removeItem('token');
  }

  checkLogin() {
    if (localStorage.getItem('token')) {
      return true;
    }
    return false;
  }

  generateBasicAuthToken(username, password) {
    return btoa(`${username}:${password}`);
  }

  getToken() {
    return localStorage.getItem('token');
  }
}
