import { Profile } from './profile';

export class Comment {
  id: number;
  content: string;
  updatedAt: string;
  createdAt: string;
  profile: Profile;
  totalPoints: number;

  constructor(
    id?: number,
    content?: string,
    updatedAt?: string,
    createdAt?: string,
    profile?: Profile,
    totalPoints?: number
  ) {
    this.id = id;
    this.content = content;
    this.updatedAt = updatedAt;
    this.createdAt = createdAt;
    this.profile = profile;
    this.totalPoints = totalPoints;
  }
}
