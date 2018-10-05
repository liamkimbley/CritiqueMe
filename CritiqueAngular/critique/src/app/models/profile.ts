export class Profile {
  id: number;
  firstName: string;
  lastName: string;
  bio: string;
  image: string;

  constructor(id?: number, firstName?: string, lastName?: string, bio?: string, image?: string) {
    this.id = id;
    this.firstName = firstName;
    this.lastName = lastName;
    this.bio = bio;
    this.image = image;
  }
}
