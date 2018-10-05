import { Injectable } from '@angular/core';
import { Observable, throwError } from 'rxjs';
import { HttpHeaders, HttpClient } from '@angular/common/http';
import { catchError } from 'rxjs/operators';
import { AuthService } from './auth.service';
import { Router } from '@angular/router';
import { Profile } from './models/profile';
import { UserService } from './user.service';

@Injectable({
  providedIn: 'root'
})
export class ProfileService {

  private url = 'http://localhost:8080/api/profile/';

  public index(): Observable<Profile []> {
    if (this.auth.checkLogin()) {
      const headers = new HttpHeaders().set(
        'Authorization', `Basic ${this.auth.getToken()}`
      );
      return this.http.get<Profile[]>(this.url, {headers})
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

  public show(id): Observable<Profile> {
    if (this.auth.checkLogin()) {
      const headers = new HttpHeaders().set(
        'Authorization', `Basic ${this.auth.getToken()}`
      );
      return this.http.get<Profile>(this.url + '/' + id, {headers})
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

  public create(prof: Profile): Observable<Profile> {
    if (this.auth.checkLogin()) {
      const httpOptions = {
        headers: new HttpHeaders({
          'Content-Type':  'application/json',
          'Authorization': `Basic ${this.auth.getToken()}`
        })
      };
      console.log(prof);
      return this.http.post<Profile>(this.url, prof, httpOptions);
    } else {
      this.router.navigateByUrl('login');
    }
  }

  public update(prof: Profile) {
    if (this.auth.checkLogin()) {
      const headers = new HttpHeaders().set(
        'Authorization', `Basic ${this.auth.getToken()}`
      );
      return this.http.put(this.url + prof.id, prof, {headers}).pipe(
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

  constructor(private userServ: UserService, private http: HttpClient, private auth: AuthService, private router: Router) { }
}
