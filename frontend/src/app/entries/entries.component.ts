import { Component, OnInit } from '@angular/core';
import { Entry } from '../entry';
import { EntryService } from '../entry.service';

@Component({
  selector: 'app-entries',
  templateUrl: './entries.component.html',
  styleUrl: './entries.component.css'
})
export class EntriesComponent implements OnInit {
  entries: Entry[] = [];
  selectedEntry?: Entry;

  constructor(private entryService: EntryService) { }

  ngOnInit(): void {
      this.getEntries();
  }

  getEntries(): void {
    this.entryService.getEntries()
      .subscribe(entries => this.entries = entries);
  }
}
