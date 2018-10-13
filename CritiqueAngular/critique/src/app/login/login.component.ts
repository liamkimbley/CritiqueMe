import { AuthService } from './../auth.service';
import { NgForm } from '@angular/forms';
import { Component, OnInit } from '@angular/core';
import { Router } from '@angular/router';
import { ProfileService } from '../profile.service';

@Component({
  selector: 'app-login',
  templateUrl: './login.component.html',
  styleUrls: ['./login.component.css']
})
export class LoginComponent implements OnInit {
  // username = '';

  login = function(userForm: NgForm) {
    // this.username = userForm.value.username;
    console.log(userForm);
    this.auth.login(userForm.value.username, userForm.value.password).subscribe(
      data => {
        console.log('data: ' + data.value);
        userForm.reset();
        this.router.navigateByUrl('home');
      },
      err => {
        this.router.navigateByUrl('login');
      }
    );
  };

  // returnUsername = function() {
  //   return this.username;
  // };

  constructor(public router: Router, private auth: AuthService, private profServ: ProfileService) {
  }

  ngOnInit() {
  }
}
