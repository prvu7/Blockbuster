package com.blockbuster.dto;

import com.fasterxml.jackson.annotation.JsonProperty;

public record MovieSearchResultDto(
        Long id,
        String title,
        @JsonProperty("release_date") String releaseDate,
        @JsonProperty("poster_path") String posterPath,
        String overview
) {}
