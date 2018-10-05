import { PostService } from './../post.service';
import { DatePipe } from '@angular/common';
import { Component, OnInit } from '@angular/core';
import { ActivatedRoute, Router } from '@angular/router';
import { HomeService } from './../home.service';
import { Post } from '../models/post';

@Component({
  selector: 'app-home',
  templateUrl: './home.component.html',
  styleUrls: ['./home.component.css']
})
export class HomeComponent implements OnInit {
  // posts: Post[] = [];
  // selected: Post = null;

  reload() {
    // this.homeService.index().subscribe(
    //   data => {
    //     console.log(data);
    //     this.posts = data;
    //   },
    //   err => console.error('Observer recieved an error: ' + err)
    // );
  }

  constructor(
    private homeService: HomeService,
    private datePipe: DatePipe,
    private activatedRoute: ActivatedRoute,
    private router: Router,
    private postService: PostService
  ) {}

  ngOnInit() {
    this.reload();
  }
}
