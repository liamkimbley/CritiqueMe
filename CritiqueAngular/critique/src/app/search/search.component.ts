import { Component, OnInit } from '@angular/core';
import { Router, ActivatedRoute } from '@angular/router';
import { SearchService } from '../search.service';
import { Post } from '../models/post';
import { Profile } from 'selenium-webdriver/firefox';
import { PostService } from '../post.service';
import { ProfileService } from '../profile.service';
import { CommentService } from '../comment.service';
import { SearchResultsService } from '../search-results.service';

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
    private commentService: CommentService,
    public router: Router,
    private searchResultsService: SearchResultsService) {}

  posts: Post[] = [];
  selectedPost: Post = null;
  profiles: Profile[] = [];
  selectedProfile: Profile = null;
  comments: Comment[] = [];
  selectedComment: Comment = null;
  searchType: String = null;
  searchString: String = null;

  ngOnInit() {
    this.reloadPosts();
    this.reloadProfiles();
    this.reloadComments();
  }

  reloadPosts = function() {
    this.postService.index().subscribe(
      data => {
        // console.log(data);
        this.posts = data;
      },
      err => {
        console.error('Observer got an error with POSTS: ' + err.status);
      }
    );
  };

  reloadProfiles = function() {
    this.profileService.show().subscribe(
      data => {
        // console.log(data);
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
        // console.log(data);
        // this.comments = data;
      },
      err => console.error('Observer recieved an error with COMMENTS: ' + err)
    );
  }

  public profileOrPost() {
    // console.log(this.searchType);
    // console.log(this.searchString);
    // this.searchType = searchType;
    // this.searchString = searchString;
      switch (this.searchType) {
        case 'firstName':
              this.displayProfilesByFirstName(this.searchString);
              break;
              case 'lastName':
              this.displayProfilesByLastName(this.searchString);
              break;
              case 'username':
              this.displayProfilesByUsername(this.searchString);
              break;
              case 'title':
              this.displayPostsByTitle(this.searchString);
              break;
            }
          }

          // posts
          displaySelectedPost = function(post: Post) {
            this.selectedPost = post;
          };

          displayProfilesByFirstName = function(firstName: String) {
            this.searchService.getProfilesByFirstName(firstName).subscribe(
              data => {
                this.searchResultsService.populateProfileArray(data);
                // this.router.navigateByUrl('/search-results');
                // tslint:disable-next-line:max-line-length
                this.router.navigateByUrl('/post', {skipLocationChange: true}).then(() => this.router.navigate(['/search-results']));
                this.searchString = null;
              },
              err => {
                console.error('Observer got an error with POSTS: ' + err.status);
              }
              );
            };

            displayProfilesByLastName = function(lastName: String) {
              this.searchService.getProfilesByLastName(lastName).subscribe(
                data => {
                  this.searchResultsService.populateProfileArray(data);
                  // this.router.navigateByUrl('/search-results');
                  // tslint:disable-next-line:max-line-length
                  this.router.navigateByUrl('/post', {skipLocationChange: true}).then(() => this.router.navigate(['/search-results']));
                  this.searchString = null;
                },
                err => {
                  console.error('Observer got an error with POSTS: ' + err.status);
                }
                );
              };

              displayProfilesByUsername = function(username: String) {
                this.searchService.getProfilesByUsername(username).subscribe(
                  data => {
                    const prof: Profile[] = [];
                    prof.push(data);
                    this.searchResultsService.populateProfileArray(prof);
                    // this.router.navigateByUrl('/search-results');
                    // tslint:disable-next-line:max-line-length
                    this.router.navigateByUrl('/post', {skipLocationChange: true}).then(() => this.router.navigate(['/search-results']));
                    this.searchString = null;
                  },
                  err => {
                    console.error('Observer got an error with POSTS: ' + err.status);
                  }
                  );
                };

                displayPostsByTitle = function(title: String) {
                  this.searchService.getPostsByTitle(title).subscribe(
                    data => {
                      this.searchResultsService.populatePostArray(data);
                      // this.router.navigateByUrl('/search-results');
                      // tslint:disable-next-line:max-line-length
                      this.router.navigateByUrl('/post', {skipLocationChange: true}).then(() => this.router.navigate(['/search-results']));
                      this.searchString = null;
                    },
                    err => {
                      console.error('Observer got an error with POSTS: ' + err.status);
                    }
                    );
                  };

                }
