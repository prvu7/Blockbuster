package com.blockbuster.dto;

import java.time.LocalDate;

public record DiaryEntryRequestDto(
        Long tmdbId,
        String movieTitle,
        String posterPath,
        LocalDate watchedDate,
        Double rating,
        String reviewText
) {}
