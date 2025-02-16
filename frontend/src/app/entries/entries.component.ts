import { Component } from '@angular/core';
import { Entry } from '../entry';

@Component({
  selector: 'app-entries',
  templateUrl: './entries.component.html',
  styleUrl: './entries.component.css'
})
export class EntriesComponent {
  entry : Entry = {
    id: 1,
    title: 'The Dark Side of the Moon',
    artist: 'Pink Floyd',
    year: '1973',
    genre: 'Progressive Rock',
    rating: 5,
    listenDate: '1973-03-01',
    review: 'A masterpiece of an album. It is a must-listen for any music lover.',
    favoriteSong: 'Time'
  }

}
