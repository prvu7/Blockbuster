package com.blockbuster.dto;

public record WatchlistItemRequestDto(
        Long tmdbId,
        String movieTitle,
        String posterPath
) {}
