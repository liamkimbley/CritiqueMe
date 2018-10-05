import { Comment } from './comment';
import { Profile } from './profile';

export class VoteKey {
  prof: Profile;
  com: Comment;
  vote: boolean;

  constructor(prof?: Profile, com?: Comment, vote?: boolean) {
    this.prof = prof;
    this.com = com;
    this.vote = vote;
  }
}
