import { AuthService } from './auth.service';
import { ProfileService } from './profile.service';
import { CommentService } from './comment.service';
import { HttpClientModule } from '@angular/common/http';
import { NgModule } from '@angular/core';
import { FormsModule } from '@angular/forms';
import { BrowserModule } from '@angular/platform-browser';
import { NgbModule } from '@ng-bootstrap/ng-bootstrap';
import { AppComponent } from './app.component';
import { HomeComponent } from './home/home.component';
import { LoginComponent } from './login/login.component';
import { LogoutComponent } from './logout/logout.component';
import { NavigationComponent } from './navigation/navigation.component';
import { PostComponent } from './post/post.component';
import { ProfileComponent } from './profile/profile.component';
import { RegisterComponent } from './register/register.component';
import { AppRoutingModule } from './app-routing.module';
import { HomeService } from './home.service';
import { PostService } from './post.service';
import { DatePipe } from '@angular/common';

@NgModule({
  declarations: [
    AppComponent,
    HomeComponent,
    LoginComponent,
    LogoutComponent,
    NavigationComponent,
    RegisterComponent,
    ProfileComponent,
    PostComponent
  ],
  imports: [
    BrowserModule,
    FormsModule,
    HttpClientModule,
    NgbModule.forRoot(),
    AppRoutingModule
  ],
  providers: [
    HomeService,
    CommentService,
    PostService,
    ProfileService,
    AuthService,
    DatePipe
  ],
  bootstrap: [AppComponent]
})
export class AppModule {}
