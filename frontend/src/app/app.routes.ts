import { Routes } from '@angular/router';
import { MovieSearch } from './movie-search/movie-search';
import { MovieDetailComponent } from './movie-detail/movie-detail';
import { LogEntry } from './log-entry/log-entry';
import { authGuardFn } from '@auth0/auth0-angular';
import { DiaryList } from './diary-list/diary-list';
import { Watchlist } from './watchlist/watchlist';

export const routes: Routes = [
    { path: 'search', component: MovieSearch },
    { path: 'movie/:id', component: MovieDetailComponent },
    { path: 'log/:tmdbId', component: LogEntry, canActivate: [authGuardFn] },
    { path: 'diary', component: DiaryList, canActivate: [authGuardFn] },
    { path: 'watchlist', component: Watchlist, canActivate: [authGuardFn] },
    { path: '', redirectTo: 'search', pathMatch: 'full' },
];
