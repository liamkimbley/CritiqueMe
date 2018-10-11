import { LoginComponent } from './../login/login.component';
import { Router } from '@angular/router';
import { AuthService } from './../auth.service';
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
import { NgModel, NgForm } from '@angular/forms';
import { NgbModal, ModalDismissReasons } from '@ng-bootstrap/ng-bootstrap';

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
  skills: Expertise[] = [];
  profSkills: Expertise[] = [];

  // posts
  posts: Post[] = [];
  selectedPost: Post = null;
  post: Post = new Post();
  editPost: Post = null;
  newPost: Post = null;
  // Modal
  closeResult: string;
  // Modal

  constructor(
    private userServ: UserService,
    private profService: ProfileService,
    private postService: PostService,
    private auth: AuthService,
    private router: Router,
    private modalService: NgbModal
  ) {}

  ngOnInit() {
    // this.reload();
    this.getOneProfile();
    this.getSkills();
  }

  // Modal
  open(content) {
    this.modalService
      .open(content, { ariaLabelledBy: 'modal-basic-title' })
      .result.then(
        result => {
          this.closeResult = `Closed with: ${result}`;
        },
        reason => {
          this.closeResult = `Dismissed ${this.getDismissReason(reason)}`;
        }
      );
  }

  private getDismissReason(reason: any): string {
    if (reason === ModalDismissReasons.ESC) {
      return 'by pressing ESC';
    } else if (reason === ModalDismissReasons.BACKDROP_CLICK) {
      return 'by clicking on a backdrop';
    } else {
      return `with: ${reason}`;
    }
  }
  // Modal

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
      data => {
        this.profile = data;
        this.selectedUser = data.user;
        this.profSkills = data.skills;
        console.log(data);
        this.loadPosts();
      },
      err => {
        console.error('Observer got an error: ' + err.status);
      }
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
        console.log(data);

        this.getOneProfile();
        this.editProfile = null;
      },
      err => {
        console.error('Observer got an error: ' + err.status);
      }
    );
  };

  deleteProfile = function(id: number) {
    this.profService.destroy(id).subscribe(
      data => {
        this.auth.logout();
        this.router.navigateByUrl('login');
      },
      err => {
        console.error('Observer got an error: ' + err.status);
      }
    );
  };

  // user's posts

  loadPosts = function() {
    console.log(this.profile.id);

    this.postService.indexByProfileId(this.profile.id).subscribe(
      data => {
        console.log(data);
        this.posts = data;
      },
      err => {
        console.error('Observer got an error: ' + err.status);
      }
    );
  };

  displayPost = function(post: Post) {
    this.selectedPost = post;
    console.log(post);
  };

  addPost = function() {
    this.newPost = new Post();
    this.newPost.profile = this.profile;
  };

  addNewPost = function() {
    this.postService.create(this.newPost).subscribe(
      data => {
        this.getOneProfile();
        this.newPost = null;
      },
      err => {
        console.error('Observer got an error: ' + err.status);
      }
    );
    console.log(this.newPost);
  };

  updatePost = function(post: NgModel) {
    console.log(post);
    this.postService.update(post).subscribe(
      data => {
        this.profile = data;
        this.editPost = null;
        this.getOneProfile();
      },
      err => {
        console.error('Observer got an error: ' + err.status);
      }
    );
  };

  deletePost = function(id: number) {
    this.postService.destroy(id).subscribe(
      data => {
        this.loadPosts();
        this.selectedPost = null;
      },
      err => {
        console.error('Observer got an error: ' + err.status);
      }
    );
  };

  getSkills = function() {
    this.profService.getAllSkills().subscribe(
      data => {
        this.skills.push(...data);
        console.log(data);
      },
      err => {
        console.error('Observer got an error: ' + err.status);
      }
    );
  };
}
