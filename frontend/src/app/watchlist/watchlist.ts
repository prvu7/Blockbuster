import { Component, inject, OnInit } from '@angular/core';
import { CommonModule } from '@angular/common';
import { Router } from '@angular/router';
import { WatchlistService } from '../services/watchlist.service';
import { WatchlistItemResponse } from '../models/watchlist-item.model';

@Component({
  selector: 'app-watchlist',
  standalone: true,
  imports: [CommonModule],
  templateUrl: './watchlist.html',
  styleUrl: './watchlist.css',
})
export class Watchlist implements OnInit {
  private watchlistService = inject(WatchlistService);
  private router = inject(Router);
  
  items: WatchlistItemResponse[] = [];
  imageBase = 'https://image.tmdb.org/t/p/w92';

  ngOnInit() {
    this.watchlistService.list().subscribe(items => this.items = items);
  }

  remove(id: number) {
    this.watchlistService.remove(id).subscribe(() => {
      this.items = this.items.filter(i => i.id !== id);
    });
  }

  logIt(item: WatchlistItemResponse) {
    this.router.navigate(['/log', item.tmdbId], {
      state: { movieTitle: item.movieTitle, posterPath: item.posterPath, fromWatchlistId: item.id }
    });
  }
}
