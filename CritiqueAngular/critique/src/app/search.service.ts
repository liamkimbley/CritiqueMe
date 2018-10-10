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
  ) { }

  public getProfilesByFirstName(firstName: String): Observable<Profile[]> {
    return this.http.get<Profile[]>(this.urlProfile + '/firstname/' + firstName).pipe(
      catchError((err: any) => {
        console.log(err);
        return throwError('Error retrieving PROFILES: ' + 'Status: ' + err);
      })
    );
  }

  public getProfilesByLastName(lastName: String): Observable<Profile[]> {
    return this.http.get<Profile[]>(this.urlProfile + '/lastname/' + lastName).pipe(
      catchError((err: any) => {
        console.log(err);
        return throwError('Error retrieving PROFILES: ' + 'Status: ' + err);
      })
    );
  }

  public getProfilesByUsername(username: String): Observable<Profile[]> {
    return this.http.get<Profile[]>(this.urlProfile + '/user/' + username).pipe(
      catchError((err: any) => {
        console.log(err);
        return throwError('Error retrieving PROFILES: ' + 'Status: ' + err);
      })
    );
  }

  public getPostsByTitle(title: String): Observable<Post[]> {
    return this.http.get<Post[]>(this.urlPost + '/title/' + title).pipe(
      catchError((err: any) => {
        console.log(err);
        return throwError('Error retrieving POSTS: ' + 'Status: ' + err);
      })
    );
  }

}
