import { Component, inject, OnInit } from '@angular/core';
import { CommonModule } from '@angular/common';
import { ActivatedRoute } from '@angular/router';
import { MovieService } from '../services/movie.service';
import { MovieDetail, CrewMember, Provider } from '../models/movie.model';

@Component({
  selector: 'app-movie-detail',
  standalone: true,
  imports: [CommonModule],
  templateUrl: './movie-detail.html',
  styleUrl: './movie-detail.css',
})
export class MovieDetailComponent implements OnInit {
  private route = inject(ActivatedRoute);
  private movieService = inject(MovieService);

  movie: MovieDetail | null = null;
  imageBase = 'https://image.tmdb.org/t/p/w342';
  region = 'US';

  ngOnInit() {
    const id = Number(this.route.snapshot.paramMap.get('id'));
    this.movieService.getDetails(id).subscribe(movie => this.movie = movie);
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
    alert('Diary logging comes in Phase 5!');
  }

  addToWatchlist() {
    alert('Watchlist comes in Phase 6!');
  }
}
