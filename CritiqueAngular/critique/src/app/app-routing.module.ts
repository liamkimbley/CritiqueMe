import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import { HomeComponent } from './home/home.component';
import { LoginComponent } from './login/login.component';
import { NavigationComponent } from './navigation/navigation.component';
import { RegisterComponent } from './register/register.component';
import { ProfileComponent } from './profile/profile.component';
import { PostComponent } from './post/post.component';
import { SearchComponent } from './search/search.component';
import { SearchResultsComponent } from './search-results/search-results.component';
import { NotFoundComponent } from './not-found/not-found.component';

const routes: Routes = [
  { path: '', component: HomeComponent },
  { path: 'home', component: HomeComponent },
  { path: 'navigation', component: NavigationComponent },
  { path: 'register', component: RegisterComponent },
  { path: 'login', component: LoginComponent },
  { path: 'post', component: PostComponent },
  { path: 'profile', component: ProfileComponent },
  { path: 'profile/:id', component: ProfileComponent },
  { path: 'profile/firstname/:firstname', component: ProfileComponent },
  { path: 'profile/lastname/:lastname', component: ProfileComponent },
  { path: 'profile/fullname/:firstname/:lastname', component: ProfileComponent },
  { path: 'profile/city/:city', component: ProfileComponent },
  { path: 'profile/state/:state', component: ProfileComponent },
  { path: 'profile/country/:country', component: ProfileComponent },
  { path: 'profile/user/:username', component: ProfileComponent },
  { path: 'categories', component: PostComponent },
  { path: 'categories/:id/posts', component: PostComponent },
  { path: 'posts/:id', component: PostComponent },
  { path: 'posts/title/:title', component: PostComponent },
  { path: 'posts/:id/comments', component: PostComponent },
  { path: 'comments/:id', component: PostComponent },
  { path: 'comments/:cid/votes', component: PostComponent },
  { path: 'comments/:cid/vote/:vid', component: PostComponent },
  { path: 'comments/:cid/vote', component: PostComponent },
  { path: 'search', component: SearchComponent },
  { path: 'search-results', component: SearchResultsComponent },
  { path: '**', component: NotFoundComponent }
  // make a not found component later
];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule { }
