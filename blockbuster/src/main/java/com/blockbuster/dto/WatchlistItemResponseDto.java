package com.blockbuster.dto;

import java.time.LocalDateTime;

public record WatchlistItemResponseDto(
        Long id,
        Long tmdbId,
        String movieTitle,
        String posterPath,
        LocalDateTime addedAt
) {}
