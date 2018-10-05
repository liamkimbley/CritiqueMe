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

  public create(comment: Comment): Observable<Comment> {
    if (this.auth.checkLogin()) {
      const httpOptions = {
        headers: new HttpHeaders({
          'Content-Type':  'application/json',
          'Authorization': `Basic ${this.auth.getToken()}`
        })
      };
      console.log(comment);
      return this.http.post<Comment>(this.url, comment, httpOptions);
    } else {
      this.router.navigateByUrl('login');
    }
  }
}
