import { Component, inject } from '@angular/core';
import { MovieService } from '../services/movie.service';
import { CommonModule } from '@angular/common';
import { FormsModule } from '@angular/forms';
import { RouterLink } from '@angular/router';
import { MovieSearchResult } from '../models/movie.model';

@Component({
  selector: 'app-movie-search',
  standalone: true,
  imports: [CommonModule, FormsModule, RouterLink],
  templateUrl: './movie-search.html',
  styleUrl: './movie-search.css',
})
export class MovieSearch {
  private movieService = inject(MovieService);

  query = '';
  results: MovieSearchResult[] = [];
  imageBase = 'https://image.tmdb.org/t/p/w200';

  search() {
    if (!this.query.trim()) return;
    this.movieService.search(this.query).subscribe(res => this.results = res.results);
  }
}
