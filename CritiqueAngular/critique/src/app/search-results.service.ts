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
  this.profiles = profiles;
}

public populatePostArray(posts: Post[]) {
  this.posts = posts;
}
}
