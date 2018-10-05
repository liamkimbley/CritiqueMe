import { Router, ActivatedRoute } from '@angular/router';
import { CategoryService } from './../category.service';
import { Component, OnInit, ViewChild } from '@angular/core';
import { MatSidenav } from '@angular/material/sidenav';
import { Category } from '../models/category';

@Component({
  selector: 'app-side-nav-left',
  templateUrl: './side-nav-left.component.html',
  styleUrls: ['./side-nav-left.component.css']
})
export class SideNavLeftComponent implements OnInit {
  constructor(
    private categoryService: CategoryService,
    private router: Router,
    private activatedRoute: ActivatedRoute
  ) {}

  @ViewChild('sidenav')
  sidenav: MatSidenav;

  reason = '';

  shouldRun = [/(^|\.)plnkr\.co$/, /(^|\.)stackblitz\.io$/].some(h =>
    h.test(window.location.host)
  );

  // Category Logic
  categories: Category[] = [];

  reload() {
    this.categoryService.index().subscribe(
      data => {
        console.log(data);
        this.categories = data;
      },
      err => console.error('Observer recieved an error: ' + err)
    );
  }
  // Category Logic

  close(reason: string) {
    this.reason = reason;
    this.sidenav.close();
  }

  ngOnInit() {
    this.reload();
  }
}
