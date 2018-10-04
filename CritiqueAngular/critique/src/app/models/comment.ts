export class Comment {
  id: number;
  content: string;
  updatedAt: string;
  createdAt: string;

  constructor(id?: number, content?: string, updatedAt?: string, createdAt?: string) {
    this.id = id;
    this.content = content;
    this.updatedAt = updatedAt;
    this.createdAt = createdAt;
  }
}
