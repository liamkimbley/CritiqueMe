import { Component, OnInit } from '@angular/core';
import { ProfileService } from '../profile.service';
import { NgForm } from '@angular/forms';
import { Profile } from '../models/profile';


@Component({
  selector: 'app-profile',
  templateUrl: './profile.component.html',
  styleUrls: ['./profile.component.css']
})
export class ProfileComponent implements OnInit {

  constructor(private profileService: ProfileService) { }

  ngOnInit() {
    this.reload();
  }

  reload = function() {

  };

  updateProfile = function(profile: NgForm) {

  };

  deleteProfile = function(profile: NgForm) {

  };

}
