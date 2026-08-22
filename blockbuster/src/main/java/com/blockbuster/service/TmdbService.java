package com.blockbuster.service;

import com.blockbuster.dto.MovieDetailDto;
import com.blockbuster.dto.MovieSearchResponseDto;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestClient;

@Service
public class TmdbService {

    private static final String BASE_URL = "https://api.themoviedb.org/3";

    private final RestClient restClient;
    private final String apiKey;

    public TmdbService(@Value("${tmdb.api.key}") String apiKey) {
        this.apiKey = apiKey;
        this.restClient = RestClient.builder().baseUrl(BASE_URL).build();
    }

    public MovieSearchResponseDto searchMovies(String query) {
        return restClient.get()
                .uri(uriBuilder -> uriBuilder
                        .path("/search/movie")
                        .queryParam("api_key", apiKey)
                        .queryParam("query", query)
                        .build())
                .retrieve()
                .body(MovieSearchResponseDto.class);
    }

    public MovieDetailDto getMovieDetails(Long tmdbId) {
        return restClient.get()
                .uri(uriBuilder -> uriBuilder
                        .path("/movie/{id}")
                        .queryParam("api_key", apiKey)
                        .queryParam("append_to_response", "credits,videos,watch/providers")
                        .build(tmdbId))
                .retrieve()
                .body(MovieDetailDto.class);
    }
}
