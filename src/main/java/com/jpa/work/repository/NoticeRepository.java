package com.jpa.work.repository;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

import org.springframework.data.domain.Limit;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.jpa.work.model.Notice;

@Repository
public interface NoticeRepository extends JpaRepository<Notice, Long> {
	Optional<List<Notice>> findByIdIn(List<Long> ids);

	List<Notice> findByOrderByCreateAtDesc(Limit limit);

	List<Notice> findByTitleAndContentAndRegDateIsGreaterThanEqual(String title, String content, LocalDateTime createAt);
}
