import { Component } from '@angular/core';
import { MessageService } from '../message.service';
import { Entry } from '../entry';
import { EntryService } from '../entry.service';

@Component({
  selector: 'app-dashboard',
  templateUrl: './dashboard.component.html',
  styleUrl: './dashboard.component.css'
})
export class DashboardComponent {
  entries: Entry[] = [];

  constructor(private entryService: EntryService, private messageService: MessageService) { }
  
  ngOnInit(): void {
    this.getEntries();
  }

  getEntries(): void {
    this.entryService.getEntries()
      .subscribe(entries => {
        this.entries = entries.slice(0, 4); // Get the top 4 entries
        this.messageService.add('Dashboard: Fetched top 4 entries');
      });
  }
}
