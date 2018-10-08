import { User } from './../models/user';
import { Expertise } from './../models/expertise';
import { Component, OnInit } from '@angular/core';
import { ProfileService } from '../profile.service';
import { Profile } from '../models/profile';
import { UserService } from '../user.service';
import { Location } from '../models/location';
import { PostService } from '../post.service';
import { Post } from '../models/post';
import { Comment } from '../models/comment';
import { NgModel } from '@angular/forms';

@Component({
  selector: 'app-profile',
  templateUrl: './profile.component.html',
  styleUrls: ['./profile.component.css']
})
export class ProfileComponent implements OnInit {

  profile: Profile = null;
  editProfile: Profile = null;
  selectedUser: User = null;
  comments: Comment[] = [];

  // posts
  posts: Post[] = [];
  selectedPost: Post = null;
  post: Post = new Post();
  editPost: Post = null;

  constructor(private userServ: UserService,
              private profService: ProfileService,
              private postService: PostService) { }

  ngOnInit() {
    // this.reload();
   this.getOneProfile();
  }

  // reload = function() {
  //   this.profService.index().subscribe(
  //     data => { this.profile = data; },
  //     err => {console.error('Observer got an error: ' + err.status); }
  //     );
  //     this.userServ.index().subscribe(
  //       data => { this.users = data; },
  //       err => {console.error('Observer got an error: ' + err.status); }
  //       );
  // };

  getOneProfile = function() {
    this.profService.show().subscribe(
      data => { this.profile = data;
        this.selectedUser = data.user;
        console.log(data);
        this.loadPosts();
      },
      err => {console.error('Observer got an error: ' + err.status); }
      );
  };

  displayTable = function() {
    this.profile = null;
    this.selectedUser = null;
  };

  setEditProfile = function() {
    this.editProfile = Object.assign({}, this.profile);
  };

  updateProfile = function(prof: Profile) {
    console.log(prof);
    this.profService.update(prof).subscribe(
      data => {
        this.profile = data;
        this.editProfile = null;
        this.getOneProfile(); },
      err => {console.error('Observer got an error: ' + err.status); }
    );
  };

  deleteProfile = function(id: number) {
    this.profService.destroy(id).subscribe(
      data => {
        this.getOneProfile(); },
      err => {console.error('Observer got an error: ' + err.status); }
    );
  };

  // user's posts

  loadPosts = function() {
    this.postService.index().subscribe(
      data => {
        console.log(data);
        this.posts = data;
      },
      err => {console.error('Observer got an error: ' + err.status); }
      );
  };

  displayPost = function(post: Post) {
    this.selectedPost = post;
    console.log(post);
  };

  // addPost = function() {
  //   this.postService.create(this.newPost).subscribe(
  //     data => {this.reload(); },
  //     err => {console.error('Observer got an error: ' + err.status); }
  //     );
  //     console.log(this.newPost);
  //     this.newPost = new Post();
  // };

  updatePost = function(post: NgModel) {
    console.log(post);
    this.postService.update(post).subscribe(
      data => {
        this.profile = data;
        this.editPost = null;
        this.getOneProfile(); },
      err => {console.error('Observer got an error: ' + err.status); }
    );
  };

  deletePost = function(id: number) {
    this.postService.destroy(id).subscribe(
      data => {
        this.getOneProfile(); },
      err => {console.error('Observer got an error: ' + err.status); }
    );
  };

  // get comments for a post


}
