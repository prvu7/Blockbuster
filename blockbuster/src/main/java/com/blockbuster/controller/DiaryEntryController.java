package com.blockbuster.controller;

import com.blockbuster.dto.DiaryEntryRequestDto;
import com.blockbuster.dto.DiaryEntryResponseDto;
import com.blockbuster.entity.DiaryEntry;
import com.blockbuster.entity.User;
import com.blockbuster.repository.DiaryEntryRepository;
import com.blockbuster.service.CurrentUserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.oauth2.jwt.Jwt;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

@RestController
@RequestMapping("/api/diary")
@RequiredArgsConstructor
public class DiaryEntryController {

    private final DiaryEntryRepository diaryEntryRepository;
    private final CurrentUserService currentUserService;

    @GetMapping
    public List<DiaryEntryResponseDto> list(@AuthenticationPrincipal Jwt jwt) {
        User user = currentUserService.getOrCreateUser(jwt);
        return diaryEntryRepository.findByUserIdOrderByWatchedDateDesc(user.getId())
                .stream().map(this::toDto).toList();
    }

    @PostMapping
    public DiaryEntryResponseDto create(@AuthenticationPrincipal Jwt jwt, @RequestBody DiaryEntryRequestDto req) {
        validateRating(req.rating());
        User user = currentUserService.getOrCreateUser(jwt);
        DiaryEntry entry = DiaryEntry.builder()
                .user(user)
                .tmdbId(req.tmdbId())
                .movieTitle(req.movieTitle())
                .posterPath(req.posterPath())
                .watchedDate(req.watchedDate())
                .rating(req.rating())
                .reviewText(req.reviewText())
                .build();
        return toDto(diaryEntryRepository.save(entry));
    }

    @PutMapping("/{id}")
    public DiaryEntryResponseDto update(@AuthenticationPrincipal Jwt jwt, @PathVariable Long id, @RequestBody DiaryEntryRequestDto req) {
        validateRating(req.rating());
        User user = currentUserService.getOrCreateUser(jwt);
        DiaryEntry entry = findOwnedEntry(id, user);
        entry.setWatchedDate(req.watchedDate());
        entry.setRating(req.rating());
        entry.setReviewText(req.reviewText());
        return toDto(diaryEntryRepository.save(entry));
    }

    @DeleteMapping("/{id}")
    public void delete(@AuthenticationPrincipal Jwt jwt, @PathVariable Long id) {
        User user = currentUserService.getOrCreateUser(jwt);
        diaryEntryRepository.delete(findOwnedEntry(id, user));
    }

    private DiaryEntry findOwnedEntry(Long id, User user) {
        return diaryEntryRepository.findById(id)
                .filter(e -> e.getUser().getId().equals(user.getId()))
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND));
    }

    private void validateRating(Double rating) {
        if (rating != null && (rating < 0.5 || rating > 5.0)) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Rating must be between 0.5 and 5.0");
        }
    }

    private DiaryEntryResponseDto toDto(DiaryEntry e) {
        return new DiaryEntryResponseDto(e.getId(), e.getTmdbId(), e.getMovieTitle(), e.getPosterPath(),
                e.getWatchedDate(), e.getRating(), e.getReviewText(), e.getCreatedAt());
    }
}

