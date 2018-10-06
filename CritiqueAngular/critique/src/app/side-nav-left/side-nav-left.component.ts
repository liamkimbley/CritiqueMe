import { PostService } from './../post.service';
import { Router, ActivatedRoute } from '@angular/router';
import { CategoryService } from './../category.service';
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

@Component({
  selector: 'app-side-nav-left',
  templateUrl: './side-nav-left.component.html',
  styleUrls: ['./side-nav-left.component.css']
})
export class SideNavLeftComponent implements OnInit, OnDestroy {
  constructor(
    private categoryService: CategoryService,
    private router: Router,
    private activatedRoute: ActivatedRoute,
    private changeDetectorRef: ChangeDetectorRef,
    private media: MediaMatcher,
    private postService: PostService
  ) {
    this.mobileQuery = media.matchMedia('(max-width: 600px)');
    this._mobileQueryListener = () => changeDetectorRef.detectChanges();
    this.mobileQuery.addListener(this._mobileQueryListener);
  }

  @ViewChild('sidenav')
  sidenav: MatSidenav;
  mobileQuery: MediaQueryList;
  private _mobileQueryListener: () => void;

  reason = '';

  // Category Logic
  categories: Category[] = [];
  selectedCategory = this.categoryService.getSelectedCategory();

  reload() {
    this.categoryService.index().subscribe(
      data => {
        console.log(data);
        this.categories = data;
      },
      err => console.error('Observer recieved an error: ' + err)
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
  // Category Logic

  close(reason: string) {
    this.reason = reason;
    this.sidenav.close();
  }

  ngOnDestroy(): void {
    this.mobileQuery.removeListener(this._mobileQueryListener);
  }

  ngOnInit() {
    this.reload();
  }
}
