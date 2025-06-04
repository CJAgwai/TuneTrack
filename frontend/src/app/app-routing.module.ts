import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import { EntriesComponent } from './entries/entries.component';
import { DashboardComponent } from './dashboard/dashboard.component';
import { EntryDetailComponent } from './entry-detail/entry-detail.component';

const routes: Routes = [
  {path: '', redirectTo: '/dashboard', pathMatch: 'full'},
  {path: 'dashboard', component: DashboardComponent},
  {path : 'entries', component: EntriesComponent},
  {path: 'detail/:id', component: EntryDetailComponent},
];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule { }
