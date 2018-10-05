export class Location {

  id: number;
  city: string;
  state: string;
  country: string;

  constructor(id?: number, city?: string, state?: string, country?: string) {
    this.id = id;
    this.city = city;
    this.state = state;
    this.country = country;
  }
}
