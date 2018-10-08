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
  login = function(userForm: NgForm) {
    console.log(userForm);
    this.auth.login(userForm.value.username, userForm.value.password).subscribe(
      data => {
        console.log('data: ' + data.value);
        userForm.reset();
        this.router.navigateByUrl('profile');
      },
      err => {
        this.router.navigateByUrl('login');
      }
    );
  };

  constructor(private router: Router, private auth: AuthService, private profServ: ProfileService) {}

  ngOnInit() {}
}
