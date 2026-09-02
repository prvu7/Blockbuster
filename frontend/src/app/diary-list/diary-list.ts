import { Component, inject, OnInit } from '@angular/core';
import { CommonModule } from '@angular/common';
import { DiaryService } from '../services/diary.service';
import { DiaryEntryResponse } from '../models/diary-entry.model';

@Component({
  selector: 'app-diary-list',
  standalone: true,
  imports: [CommonModule],
  templateUrl: './diary-list.html'
})
export class DiaryList implements OnInit {
  private diaryService = inject(DiaryService);

  entries: DiaryEntryResponse[] = [];
  imageBase = 'https://image.tmdb.org/t/p/w92';

  ngOnInit() {
    this.diaryService.list().subscribe(entries => this.entries = entries);
  }

  delete(id: number) {
    this.diaryService.delete(id).subscribe(() => {
      this.entries = this.entries.filter(e => e.id !== id);
    });
  }
}