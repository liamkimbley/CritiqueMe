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
import { DomSanitizer } from '@angular/platform-browser';
import { User } from '../models/user';
import { Profile } from '../models/profile';

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
    private profileService: ProfileService,
    private santitizer: DomSanitizer
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
  loggedInUser: Profile = null;
  image: String = null;
  video: String = null;

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
        // console.log(data);
        this.posts = data;
        this.changeToYoutubeEmbed(this.posts);
        this.getUser();
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
    // console.log(post);
  };

  goBack = function() {
    this.selected = null;
  };

  displayProfilePic = function(id: number) {
    this.profileService.getProfilePic(id);
  };

  // posts

  cleanUrl = function(url: String) {
    return this.santitizer.bypassSecurityTrustResourceUrl(url);
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
    // console.log(this.newPost);
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
        // console.log(data);
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
          // console.log('Data: ' + data.values);
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
    // console.log(comment);
    this.selectedComment = comment;
  };

  setEditComment = function() {
    this.editComment = Object.assign({}, this.selectedComment);
  };

  updateComment = function() {
    this.commentService.update(this.selectedComment, this.selectedComment.id).subscribe(
      data => {
        this.selectedComment = data;
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
        // console.log(data);
        this.comments = data;
      },
      err => console.error('Observer recieved an error: ' + err)
      );
    }

    addVote(vote: boolean, id: number) {
      const newVote: Vote = new Vote(vote);
      this.commentService.createVote(newVote, id).subscribe(
        data => {
          this.postService.show(this.selected.id).subscribe(
            data2 => {
              // console.log('***********');
              // console.log(data2);
              this.selected = data2;
            }
          );
        },
        err => console.error('Observer recieved an error: ' + err)
    );
  }

  getUser() {
    this.profileService.show().subscribe(
      data => {
        // console.log(data);
        this.loggedInUser = data;
      }
    );
  }

  ngOnDestroy(): void {
    this.mobileQuery.removeListener(this._mobileQueryListener);
  }

  ngOnInit() {
    this.reload();
    this.reloadCategories();
  }

  changeToYoutubeEmbed(posts: Post[]) {
    let element = null;

    for (let i = 0; i < posts.length; i++) {
      // console.log(posts[i]);
      element = posts[i].media;
      if (element) {
        const a: String[] = element.split('');
        // if (a.includes('youtu.be') || a.includes('youtube')) {

          // https://youtu.be/AfIOBLr1NDU copied from share link
          if (element.indexOf('youtu.be') > -1 && a[13].includes('.')) {
            console.log('********');
            a.splice(13, 1);
            a.splice(15, 0, '.');
            a.splice(16, 0, 'c');
            a.splice(17, 0, 'o');
            a.splice(18, 0, 'm');
            a.splice(19, 0, '/');
            a.splice(20, 0, 'e');
            a.splice(21, 0, 'm');
            a.splice(22, 0, 'b');
            a.splice(23, 0, 'e');
            a.splice(24, 0, 'd');
            const b: string = a.toString();
            const c: string = b.replace(/,/g, '');
            // console.log('*** youtu.be ***');
            // console.log(c);
            this.posts[i].media = c;
          }

          // https://www.youtube.com/watch?v=AfIOBLr1NDU copied from browser
          if (element.indexOf('www.youtube.com') > -1 && a[19].includes('.')) {
            a.splice(24, 0, 'e');
            a.splice(25, 0, 'm');
            a.splice(26, 0, 'b');
            a.splice(27, 0, 'e');
            a.splice(28, 0, 'd');
            a.splice(29, 0, '/');
            a.splice(30, 8);
            const link: string = a.toString();
            const browserLink: string = link.replace(/,/g, '');
            // console.log('*** youtube.com/watch ***');
            // console.log(browserLink);
            this.posts[i].media = browserLink;
          }
        }
      }
  }

  // mediaIsImage(url: String): boolean {
  //   if (url.toLowerCase().endsWith('jpg') || url.toLowerCase().endsWith('png') || url.toLowerCase().endsWith('jpeg') ||
  //       url.toLowerCase().endsWith('img') || url.toLowerCase().endsWith('gif')) {
  //     return true;
  //   }
  //   return false;
  // }

  mediaIsImage(url: String): boolean {
    if (url.toLowerCase().includes('jpg') || url.toLowerCase().includes('png') || url.toLowerCase().includes('jpeg') ||
        url.toLowerCase().includes('img') || url.toLowerCase().includes('gif')) {
      return true;
    }
    return false;
  }
}
