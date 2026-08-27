import { Routes } from '@angular/router';
import { MovieSearch } from './movie-search/movie-search';
import { MovieDetailComponent } from './movie-detail/movie-detail';

export const routes: Routes = [
    { path: 'search', component: MovieSearch },
    { path: 'movie/:id', component: MovieDetailComponent },
    { path: '', redirectTo: 'search', pathMatch: 'full' },
];
