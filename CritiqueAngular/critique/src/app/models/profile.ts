import { Location } from './location';
import { User } from './user';
export class Profile {
  id: number;
  firstName: string;
  lastName: string;
  bio: string;
  image: string;
  loc: Location;
  user: User;

  constructor(id?: number, firstName?: string, lastName?: string, bio?: string, image?: string, loc?: Location, user?: User) {
    this.id = id;
    this.firstName = firstName;
    this.lastName = lastName;
    this.bio = bio;
    this.image = image;
    this.loc = loc;
    this.user = user;
  }
}
