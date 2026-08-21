package com.blockbuster.entity;

import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.CreationTimestamp;

import java.time.LocalDateTime;

@Entity
@Table(name="watchlist_items")
@Getter @Setter @NoArgsConstructor @AllArgsConstructor @Builder
public class WatchlistItem {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(optional = false)
    @JoinColumn(name = "user_id", nullable = false)
    private User userId;

    @Column(name = "tmdb_id", nullable = false)
    private Long tmdbId;

    @CreationTimestamp
    @Builder.Default
    @Column(name = "added_at", nullable = false, updatable = false)
    private LocalDateTime createdAt = LocalDateTime.now();
}
