export class Vote {
  id: number;
  createdDate: string;

  constructor(id?: number, createdDate?: string) {
    this.id = id;
    this.createdDate = createdDate;
  }
}
