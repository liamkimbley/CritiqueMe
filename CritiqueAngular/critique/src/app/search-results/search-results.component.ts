import { Component, OnInit } from '@angular/core';
import { Post } from '../models/post';
import { Profile } from '../models/profile';
import { SearchResultsService } from '../search-results.service';

@Component({
  selector: 'app-search-results',
  templateUrl: './search-results.component.html',
  styleUrls: ['./search-results.component.css']
})
export class SearchResultsComponent implements OnInit {

  constructor(private searchResultsService: SearchResultsService) { }

  posts: Post[] = [];
  profiles: Profile[] = [];

  ngOnInit() {
    this.populateProfiles();
    this.populatePosts();
  }

  populateProfiles() {
    this.profiles = this.searchResultsService.profiles;
  }

  populatePosts() {
    this.posts = this.searchResultsService.posts;
  }

}
