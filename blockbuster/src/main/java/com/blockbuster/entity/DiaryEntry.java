package com.blockbuster.entity;

import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.CreationTimestamp;

import java.time.LocalDate;
import java.time.LocalDateTime;

@Entity
@Table(name="diary_entries")
@Getter @Setter @NoArgsConstructor @AllArgsConstructor @Builder
public class DiaryEntry {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(optional = false)
    @JoinColumn(name = "user_id", nullable = false)
    private User user;

    @Column(name = "tmdb_id", nullable = false)
    private Long tmdbId;

    @Column(name = "movie_title", nullable = false)
    private String movieTitle;

    @Column(name = "poster_path", nullable = false)
    private String posterPath;

    @Column(name = "watched_date", nullable = false)
    private LocalDate watchedDate;

    @Column(name = "rating")
    private Double rating;

    @Column(name = "review_text", length = 255)
    private String reviewText;

    @CreationTimestamp
    @Builder.Default
    @Column(name = "created_at", nullable = false, updatable = false)
    private LocalDateTime createdAt = LocalDateTime.now();
}
