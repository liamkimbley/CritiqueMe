import { VoteKey } from './vote-key';

export class Vote {
  vote: boolean;

  constructor(vote?: boolean) {
    this.vote = vote;
  }
}
