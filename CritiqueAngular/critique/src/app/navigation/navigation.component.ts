import { Router } from '@angular/router';
import { AuthService } from './../auth.service';
import { Component, OnInit } from '@angular/core';

@Component({
  selector: 'app-navigation',
  templateUrl: './navigation.component.html',
  styleUrls: ['./navigation.component.css']
})
export class NavigationComponent implements OnInit {
  navBarCheckLogin() {
    return this.authService.checkLogin();
  }
  constructor(private authService: AuthService, public router: Router) {}

  ngOnInit() {}
}
