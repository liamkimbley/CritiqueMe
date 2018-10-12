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
  private url = environment.baseUrl + 'api/posts';

  constructor(
    private http: HttpClient,
    private datePipe: DatePipe,
    private authService: AuthService
  ) {}
}
