export class Post {
  id: number;
  content: string;
  createdDate: string;
  updatedDate: string;

  constructor(id?: number, content?: string, createdDate?: string, updatedDate?: string) {
    this.id = id;
    this.content = content;
    this.createdDate = createdDate;
    this.updatedDate = updatedDate;
  }
}
