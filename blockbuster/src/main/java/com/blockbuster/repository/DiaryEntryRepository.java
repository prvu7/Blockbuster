package com.blockbuster.repository;

import com.blockbuster.entity.DiaryEntry;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface DiaryEntryRepository extends JpaRepository<DiaryEntry, Long> {
    List<DiaryEntry> findByUserIdOrderByWatchedDateDesc(Long userId);
}
