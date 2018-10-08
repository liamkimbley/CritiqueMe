import { AuthService } from './../auth.service';
import { NgForm } from '@angular/forms';
import { Component, OnInit } from '@angular/core';
import { Router } from '@angular/router';

@Component({
  selector: 'app-login',
  templateUrl: './login.component.html',
  styleUrls: ['./login.component.css']
})
export class LoginComponent implements OnInit {

  login = function(userForm: NgForm) {
    console.log(userForm);
    this.auth
      .login(userForm.value.username, userForm.value.password)
      .subscribe(data => {
        console.log('data: ' + data);
        if (data) {
          userForm.reset();
          this.router.navigateByUrl('profile');
        } else {
          this.router.navigateByUrl('login');
        }
      });
  };

  constructor(private router: Router, private auth: AuthService) { }

  ngOnInit() {
  }

}
