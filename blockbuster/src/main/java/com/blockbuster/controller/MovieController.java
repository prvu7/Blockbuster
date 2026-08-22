package com.blockbuster.controller;

import com.blockbuster.dto.MovieDetailDto;
import com.blockbuster.dto.MovieSearchResponseDto;
import com.blockbuster.service.TmdbService;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("api/movies")
public class MovieController {

    private final TmdbService tmdbService;

    public MovieController(TmdbService tmdbService) {
        this.tmdbService = tmdbService;
    }

    @GetMapping("/search")
    public MovieSearchResponseDto search(@RequestParam String query) {
        return tmdbService.searchMovies(query);
    }

    @GetMapping("/{id}")
    public MovieDetailDto getMovie(@PathVariable Long id) {
        return tmdbService.getMovieDetails(id);
    }
}
