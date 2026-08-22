package com.blockbuster.dto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.List;

@JsonIgnoreProperties(ignoreUnknown = true)
public record MovieDetailDto(
        Long id,
        String title,
        String overview,
        @JsonProperty("release_date") String releaseDate,
        @JsonProperty("poster_path") String posterPath,
        Integer runtime,
        List<GenreDto> genres,
        CreditsDto credits,
        VideosDto videos,
        @JsonProperty("watch/providers") WatchProvidersDto watchProviders
) {}
