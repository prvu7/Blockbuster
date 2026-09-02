package com.blockbuster.dto;

import java.time.LocalDate;
import java.time.LocalDateTime;

public record DiaryEntryResponseDto(
        Long id,
        Long tmdbId,
        String movieTitle,
        String posterPath,
        LocalDate watchedDate,
        Double rating,
        String reviewText,
        LocalDateTime createdAt
) {}
