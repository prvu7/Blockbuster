import { Injectable, inject } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Observable } from 'rxjs';
import { environment } from '../../environments/environment';
import { MovieSearchResponse, MovieDetail } from '../models/movie.model';

@Injectable({ providedIn: 'root' })
export class MovieService {
    private http = inject(HttpClient);
    private baseUrl = `${environment.apiBaseUrl}/movies`;

    search(query: string): Observable<MovieSearchResponse> {
        return this.http.get<MovieSearchResponse>(`${this.baseUrl}/search`, { params: { query } });
    }

    getDetails(id: number): Observable<MovieDetail> {
        return this.http.get<MovieDetail>(`${this.baseUrl}/${id}`);
    }
}