import { Component, OnInit } from '@angular/core';
import { Router } from '@angular/router';

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
        if (data) {
          this.router.navigateByUrl('posts');
        } else {
          this.router.navigateByUrl('register');
        }
       }
    );
  };

  constructor(private router: Router) { }

  ngOnInit() {
  }

}
