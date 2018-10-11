import { Injectable } from '@angular/core';
import { HttpClient, HttpHeaders } from '@angular/common/http';
import { Observable, throwError } from 'rxjs';
import { catchError, tap } from 'rxjs/operators';
import { Router } from '@angular/router';
import { AuthService } from './auth.service';
import { Post } from './models/post';
import { Category } from './models/category';
import { environment } from '../environments/environment';
import { Profile } from './models/profile';

@Injectable({
  providedIn: 'root'
})
export class SearchService {
  private uriPathProfile = 'api/profile';
  private urlProfile = environment.baseUrl + this.uriPathProfile;
  private uriPathPost = 'api/posts';
  private urlPost = environment.baseUrl + this.uriPathPost;

  constructor(
    private http: HttpClient,
    private auth: AuthService,
    private router: Router
  ) {}

  public getProfilesByFirstName(firstName: String): Observable<Profile[]> {
    if (this.auth.checkLogin()) {
      const headers = new HttpHeaders().set(
        'Authorization',
        `Basic ${this.auth.getToken()}`
      );
      return this.http
        .get<Profile[]>(this.urlProfile + '/firstname/' + firstName, {
          headers
        })
        .pipe(
          catchError((err: any) => {
            console.log(err);
            return throwError('Error retrieving PROFILES: ' + 'Status: ' + err);
          })
        );
    } else {
      this.router.navigateByUrl('login');
    }
  }

  public getProfilesByLastName(lastName: String): Observable<Profile[]> {
    if (this.auth.checkLogin()) {
      const headers = new HttpHeaders().set(
        'Authorization',
        `Basic ${this.auth.getToken()}`
      );
      return this.http
        .get<Profile[]>(this.urlProfile + '/lastname/' + lastName, { headers })
        .pipe(
          catchError((err: any) => {
            console.log(err);
            return throwError('Error retrieving PROFILES: ' + 'Status: ' + err);
          })
        );
    } else {
      this.router.navigateByUrl('login');
    }
  }

  public getProfilesByUsername(username: String): Observable<Profile[]> {
    if (this.auth.checkLogin()) {
      const headers = new HttpHeaders().set(
        'Authorization',
        `Basic ${this.auth.getToken()}`
      );
      return this.http
        .get<Profile[]>(this.urlProfile + '/user/' + username, { headers })
        .pipe(
          catchError((err: any) => {
            console.log(err);
            return throwError('Error retrieving PROFILES: ' + 'Status: ' + err);
          })
        );
    } else {
      this.router.navigateByUrl('login');
    }
  }

  public getPostsByTitle(title: String): Observable<Post[]> {
    if (this.auth.checkLogin()) {
      const headers = new HttpHeaders().set(
        'Authorization',
        `Basic ${this.auth.getToken()}`
      );
      return this.http
        .get<Post[]>(this.urlPost + '/title/' + title, { headers })
        .pipe(
          catchError((err: any) => {
            console.log(err);
            return throwError('Error retrieving POSTS: ' + 'Status: ' + err);
          })
        );
    } else {
      this.router.navigateByUrl('login');
    }
  }
}
