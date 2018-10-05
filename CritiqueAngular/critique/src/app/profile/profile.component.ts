import { Expertise } from './../models/expertise';
import { Component, OnInit } from '@angular/core';
import { ProfileService } from '../profile.service';
import { NgForm } from '@angular/forms';
import { Profile } from '../models/profile';
import { UserService } from '../user.service';
import { User } from '../models/user';
import { Location } from '../models/location';


@Component({
  selector: 'app-profile',
  templateUrl: './profile.component.html',
  styleUrls: ['./profile.component.css']
})
export class ProfileComponent implements OnInit {

  profiles: Profile[] = [];
  selected: Profile = null;
  newUser: User = new User();
  editProfile: Profile = null;
  selectedUser: User = null;
  users: User[] = [];
  selectedLoc: Location = null;
  selectedSkill: Expertise = null;

  constructor(private userServ: UserService, private user: User, private profService: ProfileService) { }

  ngOnInit() {
    this.reload();
  }

  displayTable = function() {
    this.selected = null;
    this.selectedUser = null;
  };

  reload = function() {
    this.profService.index().subscribe(
      data => { this.profiles = data; },
      err => {console.error('Observer got an error: ' + err.status); }
      );
      this.userServ.index().subscribe(
        data => { this.users = data; },
        err => {console.error('Observer got an error: ' + err.status); }
      );
  };

  // addUser = function() {
  //   this.userService.create(this.newUser).subscribe(
  //     data => {this.reload(); },
  //     err => {console.error('Observer got an error: ' + err.status); }
  //     );
  //     console.log(this.newUser);
  //     this.newUser = new Profile();
  // };

  setEditProfile = function() {
    this.editProfile = Object.assign({}, this.selected);
  };

  updateProfile = function(prof: Profile) {
    console.log(prof);
    this.profService.update(prof).subscribe(
      data => {
        this.selected = data;
        this.editProfile = null;
        this.reload(); },
      err => {console.error('Observer got an error: ' + err.status); }
    );
  };

  deleteProfile = function(id: number) {
    this.profService.destroy(id).subscribe(
      data => {
        this.reload(); },
      err => {console.error('Observer got an error: ' + err.status); }
    );
  };

}
