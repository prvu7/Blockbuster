import { Injectable, inject } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Observable } from 'rxjs';
import { environment } from '../../environments/environment';
import { DiaryEntryRequest, DiaryEntryResponse } from '../models/diary-entry.model';

@Injectable({ providedIn: 'root' })
export class DiaryService {
  private http = inject(HttpClient);
  private baseUrl = `${environment.apiBaseUrl}/diary`;

  list(): Observable<DiaryEntryResponse[]> {
    return this.http.get<DiaryEntryResponse[]>(this.baseUrl);
  }

  create(entry: DiaryEntryRequest): Observable<DiaryEntryResponse> {
    return this.http.post<DiaryEntryResponse>(this.baseUrl, entry);
  }

  delete(id: number): Observable<void> {
    return this.http.delete<void>(`${this.baseUrl}/${id}`);
  }
}