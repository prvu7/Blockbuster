export interface WatchlistItemRequest {
  tmdbId: number;
  movieTitle: string;
  posterPath: string;
}

export interface WatchlistItemResponse {
  id: number;
  tmdbId: number;
  movieTitle: string;
  posterPath: string;
  addedAt: string;
}