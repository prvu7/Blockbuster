package com.blockbuster.controller;

import com.blockbuster.dto.WatchlistItemRequestDto;
import com.blockbuster.dto.WatchlistItemResponseDto;
import com.blockbuster.service.CurrentUserService;
import com.blockbuster.service.WatchlistItemService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.oauth2.jwt.Jwt;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/watchlist")
@RequiredArgsConstructor
public class WatchlistController {

    private final WatchlistItemService watchlistItemService;
    private final CurrentUserService currentUserService;

    @GetMapping
    public List<WatchlistItemResponseDto> list(@AuthenticationPrincipal Jwt jwt) {
        return watchlistItemService.list(currentUserService.getOrCreateUser(jwt));
    }

    @PostMapping
    public WatchlistItemResponseDto add(@AuthenticationPrincipal Jwt jwt, @RequestBody WatchlistItemRequestDto req) {
        return watchlistItemService.add(currentUserService.getOrCreateUser(jwt), req);
    }

    @DeleteMapping("/{id}")
    public void remove(@AuthenticationPrincipal Jwt jwt, @PathVariable Long id) {
        watchlistItemService.remove(currentUserService.getOrCreateUser(jwt), id);
    }
}