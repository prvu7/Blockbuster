export interface MovieSearchResult {
  id: number;
  title: string;
  release_date: string;
  poster_path: string;
  overview: string;
}

export interface MovieSearchResponse {
  page: number;
  results: MovieSearchResult[];
  total_results: number;
  total_pages: number;
}

export interface Genre { id: number; name: string; }
export interface CastMember { id: number; name: string; character: string; profile_path: string; }
export interface CrewMember { id: number; name: string; job: string; department: string; }
export interface Credits { cast: CastMember[]; crew: CrewMember[]; }
export interface Video { key: string; name: string; site: string; type: string; }
export interface Videos { results: Video[]; }
export interface Provider { provider_id: number; provider_name: string; logo_path: string; }
export interface CountryProviders { link: string; flatrate?: Provider[]; rent?: Provider[]; buy?: Provider[]; }
export interface WatchProviders { results: { [countryCode: string]: CountryProviders }; }

export interface MovieDetail {
  id: number;
  title: string;
  overview: string;
  release_date: string;
  poster_path: string;
  runtime: number;
  genres: Genre[];
  credits: Credits;
  videos: Videos;
  watchProviders: WatchProviders;
}