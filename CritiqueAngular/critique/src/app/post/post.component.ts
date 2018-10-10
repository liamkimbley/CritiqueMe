import { VoteKey } from './../models/vote-key';
import { Vote } from './../models/vote';
import { Post } from '../models/post';
import { DatePipe } from '@angular/common';
import { PostService } from './../post.service';
import { Comment } from '../models/comment';
import { Router, ActivatedRoute } from '@angular/router';
import {
  Component,
  OnInit,
  ViewChild,
  ChangeDetectorRef,
  OnDestroy
} from '@angular/core';
import { MatSidenav } from '@angular/material/sidenav';
import { Category } from '../models/category';
import { MediaMatcher } from '@angular/cdk/layout';
import { Variable } from '@angular/compiler/src/render3/r3_ast';
import { NgForm } from '@angular/forms';
import { CommentService } from '../comment.service';
import { ProfileService } from '../profile.service';

@Component({
  selector: 'app-post',
  templateUrl: './post.component.html',
  styleUrls: ['./post.component.css']
})
export class PostComponent implements OnInit, OnDestroy {

  constructor(
    private postService: PostService,
    private datePipe: DatePipe,
    private activatedRoute: ActivatedRoute,
    private router: Router,
    private changeDetectorRef: ChangeDetectorRef,
    private media: MediaMatcher,
    private commentService: CommentService,
    private profileService: ProfileService
  ) {
    this.mobileQuery = media.matchMedia('(max-width: 600px)');
    this._mobileQueryListener = () => changeDetectorRef.detectChanges();
    this.mobileQuery.addListener(this._mobileQueryListener);
  }
  // User can see posts but cannot open them to view comments
  // When they click a comment, ask them to sign in or sign up
  title = 'CritiqueMe';
  posts: Post[] = [];
  selected: Post = null;
  newPost: Post = new Post();
  editPost: Post = null;
  selectedCategory: Category = { id: 1, name: 'All' };
  categories: Category[] = [];
  comments: Comment[] = [];
  mediaUrl: String = null;
  editComment: Comment = null;
  selectedComment: Comment = null;
  selCom: Comment = null;
  vote: Vote = new Vote();

  // Sidebar
  @ViewChild('sidenav')
  sidenav: MatSidenav;
  mobileQuery: MediaQueryList;
  private _mobileQueryListener: () => void;
  reason = '';
  // Sidebar


  reload = function() {
    this.postService.index().subscribe(
      data => {
        console.log(data);
        this.posts = data;
      },
      err => {
        console.error('Observer got an error: ' + err.status);
      }
    );
  };

  displaySelected = function(post: Post) {
    this.selected = post;
  };

  displayPost = function(post: Post) {
    this.selected = post;
    console.log(post);
  };

  goBack = function() {
    this.selected = null;
  };

  displayProfilePic = function(id: number) {
    this.profileService.getProfilePic(id);
  };

  addPost = function() {
    this.postService.create(this.newPost).subscribe(
      data => {
        this.reload();
      },
      err => {
        console.error('Observer got an error: ' + err.status);
      }
    );
    console.log(this.newPost);
    this.newPost = new Post();
  };

  setEditPost = function() {
    this.editPost = Object.assign({}, this.selected);
  };

  updatePost = function(post: Post) {
    console.log(post);
    this.postService.update(post).subscribe(
      data => {
        this.selected = data;
        this.editPost = null;
        this.reload();
      },
      err => {
        console.error('Observer got an error: ' + err.status);
      }
    );
  };

  deletePost = function(id: number) {
    this.postService.destroy(id).subscribe(
      data => {
        this.reload();
      },
      err => {
        console.error('Observer got an error: ' + err.status);
      }
    );
  };

  // Category Logic

  reloadCategories() {
    this.postService.indexCategories().subscribe(
      data => {
        console.log(data);
        this.categories = data;
      },
      err => console.error('Observer recieved an error: ' + err.status)
    );
  }

  searchCategory(id: number) {
    this.postService.indexPostsByCategoryId(id).subscribe(
      data => {
        if (data) {
          this.categories = data;
          console.log('Data: ' + data.values);
        } else {
          this.router.navigateByUrl('/notFound');
        }
      },
      err => console.error('Observer recieved an error: ' + err)
    );
  }

  close(reason: string) {
    this.reason = reason;
    this.sidenav.close();
  }

  // comments

  addComment = function(form: NgForm, id: number) {
    this.commentService.create(form.value, id).subscribe(
      data => {
        this.postService.show(this.selected.id).subscribe(
          data2 => {
            this.selected = data2;
            form.reset();
          }
        );
        },
      err => {console.error('Observer got an error: ' + err.status); }
      );
  };

  displayComment = function(comment: Comment) {
    console.log(comment);
    this.selectedComment = comment;
  };

  setEditComment = function() {
    this.editComment = Object.assign({}, this.selectedComment);
  };

  updateComment = function(form: NgForm, id: number) {
    this.commentService.update(form.value, id).subscribe(
      data => {
        this.postService.show(this.selected.id).subscribe(
          data2 => {
            this.selected = data2;
          }
        );
        this.selectedComment = null;
        },
      err => {console.error('Observer got an error: ' + err.status); }
    );
  };

  deleteComment = function(id: number) {
    this.commentService.destroy(id).subscribe(
      data => {
        this.postService.show(this.selected.id).subscribe(
          data2 => {
            this.selected = data2;
          }
        );
      },
      err => {console.error('Observer got an error: ' + err.status); }
    );
  };

  reloadComments() {
    this.commentService.index().subscribe(
      data => {
        console.log(data);
        this.comments = data;
      },
      err => console.error('Observer recieved an error: ' + err)
      );
    }

    setVote = function(input: boolean, id: number) {
      this.vote.vote = input;
      this.commentService.show(id).subscribe(
        data => {
          this.selCom = data;
        },
        err => console.error('Observer recieved an error: ' + err)
      );
      this.addVote();
    };

    addVote() {
      this.commentService.createVote(this.vote, this.selCom).subscribe(
        data => {
          this.vote = new Vote();
          this.selCom = null;
          this.reloadComments();
        },
        err => console.error('Observer recieved an error: ' + err)
    );
  }

  ngOnDestroy(): void {
    this.mobileQuery.removeListener(this._mobileQueryListener);
  }

  ngOnInit() {
    this.reload();
    this.reloadCategories();
  }


}
