import { HomeService } from './../home.service';
import { Component, OnInit } from '@angular/core';
import { DatePipe } from '@angular/common';
import { ActivatedRoute, Router } from '@angular/router';

@Component({
  selector: 'app-home',
  templateUrl: './home.component.html',
  styleUrls: ['./home.component.css']
})
export class HomeComponent implements OnInit {
  // User can see posts but cannot open them to view comments
  // When they click a comment, ask them to sign in or sign up

  constructor(
    private homeService: HomeService,
    private datePipe: DatePipe,
    private activatedRoute: ActivatedRoute,
    private router: Router
  ) {}

  ngOnInit() {}
}
