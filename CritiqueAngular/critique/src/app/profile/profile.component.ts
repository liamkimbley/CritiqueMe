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

  profiles: Profile[] = [];
  selected: Profile = null;
  newProfile: Profile = new Profile();
  editProfile: Profile = null;

  constructor(private profService: ProfileService) { }

  ngOnInit() {
    this.reload();
  }

  displayTable = function() {
    this.selected = null;
  };

  reload = function() {
    this.profService.index().subscribe(
      data => { this.profiles = data; },
      err => {console.error('Observer got an error: ' + err.status); }
      );
  };

  addProfile = function() {
    this.profService.create(this.newProfile).subscribe(
      data => {this.reload(); },
      err => {console.error('Observer got an error: ' + err.status); }
      );
      console.log(this.newProfile);
      this.newProfile = new Profile();
  };

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
