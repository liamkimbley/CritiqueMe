import { Location } from './location';
import { User } from './user';
import { Expertise } from './expertise';

export class Profile {
  id: number;
  firstName: string;
  lastName: string;
  bio: string;
  imageUrl: string;
  location: Location;
  user: User;
  skills: Expertise[];

  constructor(
    id?: number,
    firstName?: string,
    lastName?: string,
    bio?: string,
    image?: string,
    loc?: Location,
    user?: User,
    skills?: Expertise[]
  ) {
    this.id = id;
    this.firstName = firstName;
    this.lastName = lastName;
    this.bio = bio;
    this.imageUrl = image;
    this.location = loc;
    this.user = user;
    this.skills = skills;
  }
}
