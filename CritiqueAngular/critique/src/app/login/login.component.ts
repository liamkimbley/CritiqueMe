import { Component, OnInit } from '@angular/core';
import { Router } from '@angular/router';

@Component({
  selector: 'app-login',
  templateUrl: './login.component.html',
  styleUrls: ['./login.component.css']
})
export class LoginComponent implements OnInit {

  login = function(userForm) {
    console.log(userForm);
    this.auth
      .login(userForm.value.username, userForm.value.password)
      .subscribe(data => {
        console.log('data: ' + data);
        if (data) {
          userForm.reset();
          this.router.navigateByUrl('posts');
        } else {
          this.router.navigateByUrl('register');
        }
      });
  };

  constructor(private router: Router) { }

  ngOnInit() {
  }

}
