import { Injectable } from '@angular/core';
import { HttpClient, HttpHeaders } from '@angular/common/http';
import { Observable, throwError } from 'rxjs';
import { catchError, tap } from 'rxjs/operators';
import { Router } from '@angular/router';
import { AuthService } from './auth.service';
import { Post } from './models/post';
import { Category } from './models/category';
import { environment } from '../environments/environment';

@Injectable({
  providedIn: 'root'
})
export class PostService {
  private url = environment.baseUrl + 'api/posts';
  private catUrl = environment.baseUrl + 'api/categories';

  public index(): Observable<Post[]> {
    return this.http.get<Post[]>(this.url).pipe(
      catchError((err: any) => {
        console.log(err);
        return throwError('Error retrieving posts: ' + 'Status: ' + err);
      })
    );
  }

  public indexByProfileId(id: number): Observable<Post[]> {
    if (this.auth.checkLogin()) {
      const headers = new HttpHeaders().set(
        'Authorization', `Basic ${this.auth.getToken()}`
      );
      return this.http
        .get<Post[]>(
          environment.baseUrl + 'api/profile/' + id + '/posts', { headers })
        .pipe(
          catchError((err: any) => {
            console.log(err);
            return throwError('Error retrieving posts for profile: ' + err);
          })
        );
    } else {
      this.router.navigateByUrl('login');
    }
  }

  public indexCategories(): Observable<Category[]> {
    return this.http.get<Category[]>(this.catUrl).pipe(
      catchError((err: any) => {
        console.log(err);
        return throwError('Error retrieving categories: ' + 'Status: ' + err);
      })
    );
  }

  public indexPostsByCategoryId(id: number): Observable<Category[]> {
    return this.http
      .get<Category[]>(this.catUrl + '/' + id + '/posts')
      .pipe(
        catchError((err: any) => {
          console.log(err);
          return throwError(
            'Error retrieving posts for requested category: ' + 'Status: ' + err
          );
        })
      );
  }

  public show(id): Observable<Post> {
    if (this.auth.checkLogin()) {
      const headers = new HttpHeaders().set(
        'Authorization',
        `Basic ${this.auth.getToken()}`
      );
      return this.http.get<Post>(this.url + '/' + id, { headers }).pipe(
        catchError((err: any) => {
          console.log(err);
          return throwError('Error: ' + err.status);
        })
      );
    } else {
      this.router.navigateByUrl('login');
    }
  }

  public refreshPost(id): Observable<Post> {
    if (this.auth.checkLogin()) {
      const headers = new HttpHeaders().set(
        'Authorization',
        `Basic ${this.auth.getToken()}`
      );
      return this.http.get<Post>(this.url + '/' + id, { headers }).pipe(
        catchError((err: any) => {
          console.log(err);
          return throwError('Error: ' + err.status);
        })
      );
    } else {
      this.router.navigateByUrl('login');
    }
  }

  public create(post: Post): Observable<Post> {
    if (this.auth.checkLogin()) {
      const httpOptions = {
        headers: new HttpHeaders({
          'Content-Type': 'application/json',
          Authorization: `Basic ${this.auth.getToken()}`
        })
      };
      console.log(post);
      return this.http.post<Post>(this.url, post, httpOptions);
    } else {
      this.router.navigateByUrl('login');
    }
  }

  public update(post: Post) {
    if (this.auth.checkLogin()) {
      const headers = new HttpHeaders().set(
        'Authorization', `Basic ${this.auth.getToken()}`
      );
      return this.http.put(this.url + '/' + post.id, post, { headers }).pipe(
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
      return this.http.delete(this.url + '/' + id, { headers }).pipe(
        catchError((err: any) => {
          console.log(err);
          return throwError('Error: ' + err.status);
        })
      );
    } else {
      this.router.navigateByUrl('login');
    }
  }

  constructor(
    private http: HttpClient,
    private auth: AuthService,
    private router: Router
  ) {}
}
