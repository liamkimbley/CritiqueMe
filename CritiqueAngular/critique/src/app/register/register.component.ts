import { Component, OnInit } from '@angular/core';
import { Router } from '@angular/router';
import { AuthService } from '../auth.service';

@Component({
  selector: 'app-register',
  templateUrl: './register.component.html',
  styleUrls: ['./register.component.css']
})
export class RegisterComponent implements OnInit {
  register = function(form) {
    // console.log(form.value);
    this.auth.register(form.value).subscribe(
      data => {
        this.auth.login(form.value.username, form.value.password).subscribe(
          data2 => {this.router.navigateByUrl('profile');
                    form.reset(); },
          err2 => {console.log(err2); }

        );
      },
      err => {
        this.router.navigateByUrl('register');
      }
    );
  };

  constructor(private auth: AuthService, private router: Router) {}

  ngOnInit() {}
}
