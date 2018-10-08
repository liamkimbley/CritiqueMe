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
    console.log(form.value);
    this.auth.register(form.value).subscribe(
      data => {
        console.log('data: ' + data);
        form.reset();
        this.router.navigateByUrl('profile');
      },
      err => {
        this.router.navigateByUrl('register');
      }
    );
  };

  constructor(private auth: AuthService, private router: Router) {}

  ngOnInit() {}
}
