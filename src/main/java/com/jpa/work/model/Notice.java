package com.jpa.work.model;

import java.time.LocalDateTime;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity(name = "notice")
public class Notice extends BaseEntity {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	private String title;
	private String content;
	private LocalDateTime regDate;

	private LocalDateTime deleteTime;

	@Builder.Default
	private Long hits = 0L;

	@Builder.Default
	private Long likes = 0L;

	public void addHits() {
		this.hits = this.hits + 1;
	}

	public void changeTitle(String title) {
		this.title = title;
	}

	public void changeContent(String content) {
		this.content = content;
	}

	public void updateDeleteTimeExists() {
		this.deleteTime = LocalDateTime.now();
	}
}
