import { Injectable } from '@angular/core';
import { HttpClient, HttpHeaders } from '@angular/common/http';
import { Observable, throwError } from 'rxjs';
import { catchError, tap } from 'rxjs/operators';
import { Router } from '@angular/router';
import { AuthService } from './auth.service';
import { Post } from './models/post';

@Injectable({
  providedIn: 'root'
})
export class PostService {

  private url = 'http://localhost:8080/api/posts/';

  // public index(): Observable<Post []> {
  //   if (this.auth.checkLogin()) {
  //     const headers = new HttpHeaders().set(
  //       'Authorization', `Basic ${this.auth.getToken()}`
  //     );
  //     return this.http.get<Post[]>(this.url, {headers})
  //     .pipe(
  //       catchError((err: any) => {
  //         console.log(err);
  //         return throwError('Error: ' + err.status);
  //       })
  //       );
  //   } else {
  //       this.router.navigateByUrl('login');
  //   }
  // }

  public index(): Observable<Post[]> {
    return this.http.get<Post[]>(this.url + '?sorted=true').pipe(
      catchError((err: any) => {
        console.log(err);
        return throwError('Error retrieving posts: ' + 'Status: ' + err);

      })
    );
  }

  public show(id): Observable<Post> {
    if (this.auth.checkLogin()) {
      const headers = new HttpHeaders().set(
        'Authorization', `Basic ${this.auth.getToken()}`
      );
      return this.http.get<Post>(this.url + '/' + id, {headers})
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

  public create(post: Post): Observable<Post> {
    if (this.auth.checkLogin()) {
      const httpOptions = {
        headers: new HttpHeaders({
          'Content-Type':  'application/json',
          'Authorization': `Basic ${this.auth.getToken()}`
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
      return this.http.put(this.url + post.id, post, {headers}).pipe(
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
