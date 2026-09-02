import { Component, inject, OnInit } from '@angular/core';
import { CommonModule, Location } from '@angular/common';
import { FormsModule } from '@angular/forms';
import { ActivatedRoute, Router } from '@angular/router';
import { DiaryService } from '../services/diary.service';
import { WatchlistService } from '../services/watchlist.service';

interface LogEntryNavState {
  movieTitle?: string;
  posterPath?: string;
  fromWatchlistId?: number;
}

@Component({
  selector: 'app-log-entry',
  standalone: true,
  imports: [CommonModule, FormsModule],
  templateUrl: './log-entry.html'
})
export class LogEntry implements OnInit {
  private route = inject(ActivatedRoute);
  private router = inject(Router);
  private location = inject(Location);
  private diaryService = inject(DiaryService);
  private watchlistService = inject(WatchlistService);

  private fromWatchlistId: number | null = null;
  tmdbId!: number;
  movieTitle = '';
  posterPath = '';
  watchedDate = new Date().toISOString().slice(0, 10);
  rating = 0;
  reviewText = '';
  ratingOptions = [0.5, 1, 1.5, 2, 2.5, 3, 3.5, 4, 4.5, 5];

  ngOnInit() {
    this.tmdbId = Number(this.route.snapshot.paramMap.get('tmdbId'));
    this.movieTitle = this.route.snapshot.queryParamMap.get('title') ?? '';
    this.posterPath = this.route.snapshot.queryParamMap.get('poster') ?? '';
    const state = this.location.getState() as LogEntryNavState | null;
    if (state?.movieTitle) {
      this.movieTitle = state.movieTitle;
      this.posterPath = state.posterPath ?? '';
      this.fromWatchlistId = state.fromWatchlistId ?? null;
    }
  }

  submit() {
    this.diaryService.create({
      tmdbId: this.tmdbId,
      movieTitle: this.movieTitle,
      posterPath: this.posterPath,
      watchedDate: this.watchedDate,
      rating: this.rating === 0 ? null : this.rating,
      reviewText: this.reviewText
    }).subscribe(() => {
      if (this.fromWatchlistId !== null) {
        this.watchlistService.remove(this.fromWatchlistId).subscribe({
          next: () => this.router.navigate(['/diary']),
          error: () => this.router.navigate(['/diary'])
        });
      } else {
        this.router.navigate(['/diary']);
      }
    });
  }
}