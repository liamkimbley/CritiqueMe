import { Component, OnInit } from '@angular/core';
import { AuthService } from '../auth.service';
import { Router } from '@angular/router';

@Component({
  selector: 'app-logout',
  templateUrl: './logout.component.html',
  styleUrls: ['./logout.component.css']
})
export class LogoutComponent implements OnInit {

  logout = function() {
    console.log('Logout');
    this.auth.logout();
    const logged = this.auth.checkLogin();
    if (logged) {
      this.router.navigateByUrl('posts');
    } else {
      this.router.navigateByUrl('home');
    }
  };

  constructor(private auth: AuthService, private router: Router) { }

  ngOnInit() {
  }

}
