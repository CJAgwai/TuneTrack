import { Component, Input} from '@angular/core';

@Component({
  selector: 'app-searchbar',
  templateUrl: './searchbar.component.html',
  styleUrl: './searchbar.component.css'
})
export class SearchbarComponent {

searchTerm: string = '';
data = ['Angular', 'React', 'Vue', 'Svelte'];

get filteredData() {
  return this.data.filter(item => 
    item.toLowerCase().includes(this.searchTerm.toLowerCase())
  );
}
}
