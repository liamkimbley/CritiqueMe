import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { environment } from '../environments/environment';
import { Category } from './models/category';
import { Observable, throwError } from 'rxjs';
import { catchError } from 'rxjs/operators';

@Injectable({
  providedIn: 'root'
})
export class CategoryService {
  private uriPath = 'api/categories';
  private url = environment.baseUrl + this.uriPath;

  public index(): Observable<Category[]> {
    return this.http.get<Category[]>(this.url + '?sorted=true').pipe(
      catchError((err: any) => {
        console.log(err);
        return throwError('Error retrieving categories: ' + 'Status: ' + err);
      })
    );
  }

  // public indexCategory(id: number): Observable<Category[]> {
  //   return this.http.get<Category[]>(this.url + '/' + id + '/posts' + '?sorted=true').pipe(
  //     catchError((err: any) => {
  //       console.log(err);
  //       return throwError('Error retrieving posts for requested category: ' + 'Status: ' + err);
  //     })
  //   );
  // }
  constructor(private http: HttpClient) {}
}
