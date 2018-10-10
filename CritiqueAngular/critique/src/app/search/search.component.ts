import { Component, OnInit } from '@angular/core';
import { Router, ActivatedRoute } from '@angular/router';
import { SearchService } from '../search.service';
import { Post } from '../models/post';
import { Profile } from 'selenium-webdriver/firefox';
import { PostService } from '../post.service';
import { ProfileService } from '../profile.service';
import { CommentService } from '../comment.service';

@Component({
  selector: 'app-search',
  templateUrl: './search.component.html',
  styleUrls: ['./search.component.css']
})
export class SearchComponent implements OnInit {

  constructor(
    private searchService: SearchService,
    private postService: PostService,
    private profileService: ProfileService,
    private commentService: CommentService) { }

  // posts
  posts: Post[] = [];
  selectedPost: Post = null;

  // profiles
  profiles: Profile[] = [];
  selectedProfile: Profile = null;

  // comments
  comments: Comment[] = [];
  selectedComment: Comment = null;

  ngOnInit() {
    this.reloadPosts();
    this.reloadProfiles();
    this.reloadComments();
  }

  reloadPosts = function() {
    this.postService.index().subscribe(
      data => {
        console.log(data);
        this.posts = data;
      },
      err => {
        console.error('Observer got an error with POSTS: ' + err.status);
      }
    );
  };

  reloadProfiles = function() {
    this.profileService.index().subscribe(
      data => {
        console.log(data);
        this.profiles = data;
      },
      err => {
        console.error('Observer got an error with PROFILES: ' + err.status);
      }
    );
  };

  reloadComments() {
    this.commentService.index().subscribe(
      data => {
        console.log(data);
        // this.comments = data;
      },
      err => console.error('Observer recieved an error with COMMENTS: ' + err)
    );
  }

  public profileOrPost(userSelection: String) {
    //   if (userSelection) === 'profile' {
    //      call all get profile methods
    //   }
    //   if (userSelection) === 'post' {
    //      call all get post methods
    //   }

  }

  // posts
  displaySelectedPost = function(post: Post) {
    this.selectedPost = post;
  };

  displayProfilesByFirstName = function(firstName: String) {
    this.searchService.getProfilesByFirstName(firstName).subscribe(
      data => {
        this.reloadPosts();
      },
      err => {
        console.error('Observer got an error with POSTS: ' + err.status);
      }
    );
  };

}
