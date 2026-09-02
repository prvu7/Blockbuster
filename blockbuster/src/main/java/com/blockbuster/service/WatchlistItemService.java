package com.blockbuster.service;

import com.blockbuster.dto.WatchlistItemRequestDto;
import com.blockbuster.dto.WatchlistItemResponseDto;
import com.blockbuster.entity.User;
import com.blockbuster.entity.WatchlistItem;
import com.blockbuster.repository.WatchlistItemRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

@Service
@RequiredArgsConstructor
public class WatchlistItemService {

    private final WatchlistItemRepository watchlistItemRepository;

    public List<WatchlistItemResponseDto> list(User user) {
        return watchlistItemRepository.findByUserIdOrderByAddedAtDesc(user.getId())
                .stream().map(this::toDto).toList();
    }

    public WatchlistItemResponseDto add(User user, WatchlistItemRequestDto req) {
        return watchlistItemRepository.findByUserIdAndTmdbId(user.getId(), req.tmdbId())
                .map(this::toDto)
                .orElseGet(() -> {
                    WatchlistItem item = WatchlistItem.builder()
                            .user(user)
                            .tmdbId(req.tmdbId())
                            .movieTitle(req.movieTitle())
                            .posterPath(req.posterPath())
                            .build();
                    return toDto(watchlistItemRepository.save(item));
                });
    }

    public void remove(User user, Long id) {
        WatchlistItem item = watchlistItemRepository.findById(id)
                .filter(w -> w.getUser().getId().equals(user.getId()))
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND));
        watchlistItemRepository.delete(item);
    }

    private WatchlistItemResponseDto toDto(WatchlistItem w) {
        return new WatchlistItemResponseDto(w.getId(), w.getTmdbId(), w.getMovieTitle(), w.getPosterPath(), w.getAddedAt());
    }
}