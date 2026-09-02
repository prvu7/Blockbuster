import { Component, inject, OnInit } from '@angular/core';
import { CommonModule } from '@angular/common';
import { ActivatedRoute, Router } from '@angular/router';
import { MovieService } from '../services/movie.service';
import { take } from 'rxjs';
import { MovieDetail, CrewMember, Provider } from '../models/movie.model';
import { WatchlistService } from '../services/watchlist.service';
import { AuthService } from '@auth0/auth0-angular';

@Component({
  selector: 'app-movie-detail',
  standalone: true,
  imports: [CommonModule],
  templateUrl: './movie-detail.html',
  styleUrl: './movie-detail.css',
})
export class MovieDetailComponent implements OnInit {
  private route = inject(ActivatedRoute);
  private router = inject(Router);
  private movieService = inject(MovieService);
  private watchlistService = inject(WatchlistService);
  private auth = inject(AuthService);

  movie: MovieDetail | null = null;
  imageBase = 'https://image.tmdb.org/t/p/w342';
  region = 'US';
  addedToWatchlist = false;
  loadError = false;

  ngOnInit() {
    const id = Number(this.route.snapshot.paramMap.get('id'));
    this.movieService.getDetails(id).subscribe({
      next: (movie) => this.movie = movie,
      error: err => {
        this.loadError = true;
        console.error('Error loading movie details:', err);
      }
    });
  }

  get director(): CrewMember | undefined {
    return this.movie?.credits.crew.find(c => c.job === "Director");
  }

  get trailerKey(): string | undefined {
    return this.movie?.videos.results.find(v => v.site === 'YouTube' && v.type === 'Trailer')?.key;
  }

  get providers(): Provider[] {
    return this.movie?.watchProviders?.results?.[this.region]?.flatrate ?? [];
  }

  logMovie() {
    this.router.navigate(['/log', this.movie!.id], {
      queryParams: { title: this.movie!.title, poster: this.movie!.poster_path }
    });
  }

  addToWatchlist() {
    this.auth.isAuthenticated$.pipe(take(1)).subscribe(isAuthenticated => {
      if (!isAuthenticated) {
        this.auth.loginWithRedirect({ appState: { target: this.router.url } });
        return;
      }
      this.watchlistService.add({
        tmdbId: this.movie!.id,
        movieTitle: this.movie!.title,
        posterPath: this.movie!.poster_path
      }).subscribe(() => this.addedToWatchlist = true);
    });
  }
}
