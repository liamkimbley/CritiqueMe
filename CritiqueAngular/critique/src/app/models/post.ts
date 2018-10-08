import { Category } from './category';
import { Comment } from './comment';
import { Profile } from './profile';
export class Post {
  id: number;
  content: string;
  createdDate: string;
  updatedDate: string;
  title: string;
  media: string;
  categories: Category[];
  comments: Comment[];
  profile: Profile;

  constructor(
    id?: number,
    title?: string,
    media?: string,
    content?: string,
    createdDate?: string,
    updatedDate?: string,
    categories?: Category[],
    comments?: Comment[],
    profile?: Profile
  ) {
    this.id = id;
    this.content = content;
    this.createdDate = createdDate;
    this.updatedDate = updatedDate;
    this.title = title;
    this.media = media;
    this.categories = categories;
    this.comments = comments;
    this.profile = profile;
  }
}
