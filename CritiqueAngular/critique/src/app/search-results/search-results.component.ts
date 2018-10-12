import { Component, OnInit } from '@angular/core';
import { Post } from '../models/post';
import { Profile } from '../models/profile';
import { SearchResultsService } from '../search-results.service';
import { DomSanitizer } from '@angular/platform-browser';

@Component({
  selector: 'app-search-results',
  templateUrl: './search-results.component.html',
  styleUrls: ['./search-results.component.css']
})
export class SearchResultsComponent implements OnInit {

  constructor(
    private searchResultsService: SearchResultsService,
    private santitizer: DomSanitizer) { }

  posts: Post[] = [];
  profiles: Profile[] = [];
  selected: Post = null;

  ngOnInit() {
    this.reload();
  }

  reload() {
    this.populateProfiles();
    this.populatePosts();
    this.changeToYoutubeEmbed(this.posts);
  }

  displaySelectedPost = function(post: Post) {
    this.selected = post;
  };

  populateProfiles() {
    this.profiles = this.searchResultsService.profiles;
  }

  populatePosts() {
    this.posts = this.searchResultsService.posts;
  }

  cleanUrl = function(url: String) {
    return this.santitizer.bypassSecurityTrustResourceUrl(url);
  };

  changeToYoutubeEmbed(posts: Post[]) {
    let element = null;

    for (let i = 0; i < posts.length; i++) {
      // console.log(posts[i]);
      element = posts[i].media;
      if (element) {
        const a: String[] = element.split('');
        // if (a.includes('youtu.be') || a.includes('youtube')) {

          // https://youtu.be/AfIOBLr1NDU copied from share link
          if (element.indexOf('youtu.be') > -1 && a[13].includes('.')) {
            console.log('********');
            a.splice(13, 1);
            a.splice(15, 0, '.');
            a.splice(16, 0, 'c');
            a.splice(17, 0, 'o');
            a.splice(18, 0, 'm');
            a.splice(19, 0, '/');
            a.splice(20, 0, 'e');
            a.splice(21, 0, 'm');
            a.splice(22, 0, 'b');
            a.splice(23, 0, 'e');
            a.splice(24, 0, 'd');
            const b: string = a.toString();
            const c: string = b.replace(/,/g, '');
            // console.log('*** youtu.be ***');
            // console.log(c);
            this.posts[i].media = c;
          }

          // https://www.youtube.com/watch?v=AfIOBLr1NDU copied from browser
          if (element.indexOf('www.youtube.com') > -1 && a[19].includes('.')) {
            a.splice(24, 0, 'e');
            a.splice(25, 0, 'm');
            a.splice(26, 0, 'b');
            a.splice(27, 0, 'e');
            a.splice(28, 0, 'd');
            a.splice(29, 0, '/');
            a.splice(30, 8);
            const link: string = a.toString();
            const browserLink: string = link.replace(/,/g, '');
            // console.log('*** youtube.com/watch ***');
            // console.log(browserLink);
            this.posts[i].media = browserLink;
          }
        }
      }
  }

  mediaIsImage(url: String): boolean {
    if (url.toLowerCase().includes('jpg') || url.toLowerCase().includes('png') || url.toLowerCase().includes('jpeg') ||
        url.toLowerCase().includes('img') || url.toLowerCase().includes('gif')) {
      return true;
    }
    return false;
  }

}
