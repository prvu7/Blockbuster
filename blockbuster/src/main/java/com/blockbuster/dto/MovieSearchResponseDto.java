package com.blockbuster.dto;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.List;

public record MovieSearchResponseDto(
        int page,
        List<MovieSearchResultDto> results,
        @JsonProperty("total_results") int totalResults,
        @JsonProperty("total_pages") int totalPages
) {}
