import { catchError } from 'rxjs/operators';
import { AuthService } from './auth.service';
import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { DatePipe } from '@angular/common';
import { Observable, throwError } from 'rxjs';
import { Post } from './models/post';
import { environment } from '../environments/environment';

@Injectable({
  providedIn: 'root'
})
export class HomeService {
  private uriPath = 'api/posts';
  private url = environment.baseUrl + this.uriPath;

  public index(): Observable<Post[]> {
    return this.http.get<Post[]>(this.url + '?sorted=true').pipe(
      catchError((err: any) => {
        console.log(err);
        return throwError('Error retrieving posts: ' + 'Status: ' + err);

      })
    );
  }
  constructor(
    private http: HttpClient,
    private datePipe: DatePipe,
    private authService: AuthService
  ) {}
}
