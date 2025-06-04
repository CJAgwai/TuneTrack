import {Component, Input} from '@angular/core';
import {NgIf, UpperCasePipe} from '@angular/common';
import {FormsModule} from '@angular/forms';
import {Entry} from '../entry';
import { ActivatedRoute } from '@angular/router';
import { EntryService } from '../entry.service';
import { Location } from '@angular/common';

@Component({
  standalone: true,
  selector: 'app-entry-detail',
  templateUrl: './entry-detail.component.html',
  styleUrls: ['./entry-detail.component.css'],
  imports: [FormsModule, NgIf, UpperCasePipe],
})
export class EntryDetailComponent {
  entry: Entry | undefined;
  
  constructor(
    private route: ActivatedRoute,
    private entryService: EntryService,
    private location: Location
  ) {}

  ngOnInit(): void {
    this.getHero();
  }

  getHero(): void {
    const id = Number(this.route.snapshot.paramMap.get('id'));
    this.entryService.getEntry(id)
      .subscribe(entry => this.entry = entry);
  }

  goBack(): void {
    this.location.back();
  }

}