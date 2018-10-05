import { VoteKey } from './vote-key';

export class Vote {
  id: VoteKey;
  createdDate: string;

  constructor(id?: VoteKey, createdDate?: string) {
    this.id = id;
    this.createdDate = createdDate;
  }
}
