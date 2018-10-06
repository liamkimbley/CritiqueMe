import { Category } from './category';
export class Post {
  id: number;
  content: string;
  createdDate: string;
  updatedDate: string;
  title: string;
  media: string;
  categories: Category[];

  constructor(
    id?: number,
    title?: string,
    media?: string,
    content?: string,
    createdDate?: string,
    updatedDate?: string,
    categories?: Category[]
  ) {
    this.id = id;
    this.content = content;
    this.createdDate = createdDate;
    this.updatedDate = updatedDate;
    this.title = title;
    this.media = media;
    this.categories = categories;
  }
}
