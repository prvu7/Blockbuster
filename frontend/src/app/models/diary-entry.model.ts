export interface DiaryEntryRequest {
  tmdbId: number;
  movieTitle: string;
  posterPath: string;
  watchedDate: string;
  rating: number | null;
  reviewText: string;
}

export interface DiaryEntryResponse {
  id: number;
  tmdbId: number;
  movieTitle: string;
  posterPath: string;
  watchedDate: string;
  rating: number | null;
  reviewText: string;
  createdAt: string;
}