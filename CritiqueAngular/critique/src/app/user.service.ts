import { environment } from './../environments/environment.prod';
import { Injectable } from '@angular/core';
import { HttpHeaders, HttpClient } from '@angular/common/http';
import { catchError } from 'rxjs/operators';
import { AuthService } from './auth.service';
import { Router } from '@angular/router';
import { User } from './models/user';
import { Observable, throwError } from 'rxjs';

@Injectable({
  providedIn: 'root'
})
export class UserService {

  // private url = 'http://localhost:8080/api/users/';
  private url = environment.baseUrl + 'api/users/';

  public index(): Observable<User []> {
    if (this.auth.checkLogin()) {
      const headers = new HttpHeaders().set(
        'Authorization', `Basic ${this.auth.getToken()}`
      );
      return this.http.get<User[]>(this.url, {headers})
      .pipe(
        catchError((err: any) => {
          console.log(err);
          return throwError('Error: ' + err.status);
        })
        );
    } else {
        this.router.navigateByUrl('login');
    }
  }

  public show(id): Observable<User> {
    if (this.auth.checkLogin()) {
      const headers = new HttpHeaders().set(
        'Authorization', `Basic ${this.auth.getToken()}`
      );
      return this.http.get<User>(this.url + '/' + id, {headers})
           .pipe(
              catchError((err: any) => {
              console.log(err);
              return throwError('Error: ' + err.status);
            })
        );
    } else {
        this.router.navigateByUrl('login');
    }
  }

  public create(user: User): Observable<User> {
    if (this.auth.checkLogin()) {
      const httpOptions = {
        headers: new HttpHeaders({
          'Content-Type':  'application/json',
          'Authorization': `Basic ${this.auth.getToken()}`
        })
      };
      console.log(user);
      return this.http.post<User>(this.url, user, httpOptions);
    } else {
      this.router.navigateByUrl('login');
    }
  }

  public update(user: User) {
    if (this.auth.checkLogin()) {
      const headers = new HttpHeaders().set(
        'Authorization', `Basic ${this.auth.getToken()}`
      );
      return this.http.put(this.url + user.id, user, {headers}).pipe(
          catchError((err: any) => {
          console.log(err);
          return throwError('Error: ' + err.status);
        })
      );
    } else {
      this.router.navigateByUrl('login');
    }
 }

  public destroy(id: number) {
    if (this.auth.checkLogin()) {
      const headers = new HttpHeaders().set(
        'Authorization', `Basic ${this.auth.getToken()}`
      );
        return this.http.delete(this.url + id, {headers}).pipe(
          catchError((err: any) => {
          console.log(err);
          return throwError('Error: ' + err.status);
        })
      );
    } else {
      this.router.navigateByUrl('login');
    }
  }

  constructor(private http: HttpClient, private auth: AuthService, private router: Router) { }
}
