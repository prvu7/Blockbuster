import { Injectable, inject } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Observable } from 'rxjs';
import { environment } from '../../environments/environment';
import { WatchlistItemRequest, WatchlistItemResponse } from '../models/watchlist-item.model';

@Injectable({ providedIn: 'root' })
export class WatchlistService {
  private http = inject(HttpClient);
  private baseUrl = `${environment.apiBaseUrl}/watchlist`;

  list(): Observable<WatchlistItemResponse[]> {
    return this.http.get<WatchlistItemResponse[]>(this.baseUrl);
  }

  add(item: WatchlistItemRequest): Observable<WatchlistItemResponse> {
    return this.http.post<WatchlistItemResponse>(this.baseUrl, item);
  }

  remove(id: number): Observable<void> {
    return this.http.delete<void>(`${this.baseUrl}/${id}`);
  }
}