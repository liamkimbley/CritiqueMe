import { Category } from './models/category';
import { Post } from './models/post';
import { Pipe, PipeTransform } from '@angular/core';

@Pipe({
  name: 'category'
})
export class CategoryPipe implements PipeTransform {

  transform(value: Post[], category: Category): any {
    // console.log(value);
    const results = [];
    if (category.name === 'All') {
      return value;
    }
    // go through value list, add pokemon to results if it has type
    for (let i = 0; i < value.length; i++) {
      const element = value[i];
      // console.log(element);
      for (let j = 0; j < element.categories.length; j++) {
        const type = element.categories[j];
        if (type.name === category.name) {
          results.push(element);
          break;
        }
      }
    }
    if (results.length === 0) {
      return value;
    }
    return results;
  }

}
