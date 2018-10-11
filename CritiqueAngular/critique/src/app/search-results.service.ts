import { Injectable } from '@angular/core';
import { Profile } from './models/profile';
import { Post } from './models/post';

@Injectable({
  providedIn: 'root'
})
export class SearchResultsService {
  profiles: Profile[] = [];
  posts: Post[] = [];

  constructor() { }

public populateProfileArray(profiles: Profile[]) {
  this.profiles = [];
  this.profiles = profiles;
  this.posts = [];
}

public populatePostArray(posts: Post[]) {
  this.posts = [];
  this.posts = posts;
  this.profiles = [];
}
}
