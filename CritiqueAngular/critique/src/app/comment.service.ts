import { Vote } from './models/vote';
import { Injectable } from '@angular/core';
import { Observable, throwError } from 'rxjs';
import { HttpHeaders, HttpClient } from '@angular/common/http';
import { catchError } from 'rxjs/operators';
import { AuthService } from './auth.service';
import { Router } from '@angular/router';
import { Comment } from './models/comment';

@Injectable({
  providedIn: 'root'
})
export class CommentService {
  private url = 'http://localhost:8080/api/posts/';
  private commentUrl = 'http://localhost:8080/api/comments/';

  constructor(private http: HttpClient, private auth: AuthService, private router: Router) { }

  public index(): Observable<Comment []> {
    if (this.auth.checkLogin()) {
      const headers = new HttpHeaders().set(
        'Authorization', `Basic ${this.auth.getToken()}`
      );
      return this.http.get<Comment[]>(this.url, {headers})
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

  public show(id: number): Observable<Comment> {
    if (this.auth.checkLogin()) {
      const httpOptions = {
        headers: new HttpHeaders({
          'Content-Type':  'application/json',
          'Authorization': `Basic ${this.auth.getToken()}`
        })
      };
      return this.http.get<Comment>(this.commentUrl + id, httpOptions).pipe(
        catchError((err: any) => {
          console.log(err);
          return throwError('Error: ' + err.status);
        })
      );
    } else {
      this.router.navigateByUrl('login');
    }
  }

  public create(comment: Comment, id: number): Observable<Comment> {
    if (this.auth.checkLogin()) {
      const httpOptions = {
        headers: new HttpHeaders({
          'Content-Type':  'application/json',
          'Authorization': `Basic ${this.auth.getToken()}`
        })
      };
      // console.log(comment);
      // console.log(id);

      return this.http.post<Comment>(this.url + id + '/comments', comment, httpOptions);
    } else {
      this.router.navigateByUrl('login');
    }
  }

  public update(comment: Comment, id: number): Observable<Comment> {
    if (this.auth.checkLogin()) {
      const headers = new HttpHeaders().set(
        'Authorization',
        `Basic ${this.auth.getToken()}`
      );
      return this.http.put<Comment>(this.commentUrl + id, comment, {headers}).pipe(
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
    // if (this.auth.checkLogin()) {
    //   const headers = new HttpHeaders().set(
    //     'Authorization',
    //     `Basic ${this.auth.getToken()}`
    //   );
    //   return this.http.delete(this.url + id, { headers }).pipe(
    //     catchError((err: any) => {
    //       console.log(err);
    //       return throwError('Error: ' + err.status);
    //     })
    //   );
    // } else {
    //   this.router.navigateByUrl('login');
    // }
    if (this.auth.checkLogin()) {
      const httpOptions = {
        headers: new HttpHeaders({
          'Content-Type':  'application/json',
          'Authorization': `Basic ${this.auth.getToken()}`
        })
      };
      return this.http.delete(this.commentUrl + id, httpOptions).pipe(
        catchError((err: any) => {
          console.log(err);
          return throwError('Error: ' + err.status);
        })
      );
    } else {
      this.router.navigateByUrl('login');
    }
  }

  public createVote(vote: Vote, id: number) {
    if (this.auth.checkLogin()) {
      const httpOptions = {
        headers: new HttpHeaders({
          'Content-Type':  'application/json',
          'Authorization': `Basic ${this.auth.getToken()}`
        })
      };
      console.log(vote);
      return this.http.post<Vote>(this.commentUrl + id + '/votes', vote, httpOptions).pipe(
        catchError((err: any) => {
          console.log(err);
          return throwError('Error: ' + err.status);
        })
      );
    } else {
      this.router.navigateByUrl('login');
    }
  }
}
