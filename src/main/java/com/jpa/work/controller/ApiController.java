package com.jpa.work.controller;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import org.springframework.data.domain.Limit;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.jpa.work.model.Notice;
import com.jpa.work.model.dto.NoticeDeleteDto;
import com.jpa.work.model.dto.NoticeDto;
import com.jpa.work.service.NoticeService;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@RequiredArgsConstructor
@RestController
@RequestMapping
public class ApiController {

	private final NoticeService noticeService;

	@GetMapping("/api/notice")
	public List<Notice> notice() {
		log.info("notice");
		ArrayList<Notice> notices = new ArrayList<>();


		Notice notice1 = Notice.builder()
			.id(1L)
			.title("공지사항")
			.content("공지사항 내용")
			.regDate(LocalDateTime.now())
			.build();
		Notice notice2 = Notice.builder()
			.id(2L)
			.title("공지사항2")
			.content("공지사항 내용2")
			.regDate(LocalDateTime.now())
			.build();
		notices.add(notice1);
		notices.add(notice2);
		return notices;
	}

	@GetMapping("/api/notice/count")
	public int noticeCount() {
		log.info("noticeCount");
		log.info("notice");
		ArrayList<Notice> notices = new ArrayList<>();


		Notice notice1 = Notice.builder()
			.id(1L)
			.title("공지사항")
			.content("공지사항 내용")
			.regDate(LocalDateTime.now())
			.build();
		Notice notice2 = Notice.builder()
			.id(2L)
			.title("공지사항2")
			.content("공지사항 내용2")
			.regDate(LocalDateTime.now())
			.build();
		notices.add(notice1);
		notices.add(notice2);
		int size = notices.size();
		return size;
	}


	@PostMapping("/api/notice")
	public ResponseEntity<?> noticePost(@Valid @RequestBody NoticeDto noticeDto, Errors errors) {
		ResponseEntity<?> register = noticeService.register(noticeDto,errors);
		return register;
	}

	@GetMapping("/api/notice/{noticeId}")
	public Notice noticeGet(@PathVariable Long noticeId) {
		Notice detail = noticeService.getDetail(noticeId);
		return detail;
	}

	@PutMapping("/api/notice/{noticeId}")
	public Notice noticeEdit(@PathVariable Long noticeId,@RequestBody NoticeDto noticeDto) {
		Notice notice = noticeService.editNotice(noticeId, noticeDto);
		return notice;
	}

	@DeleteMapping("/api/notice/{noticeId}")
	public void noticeDelete(@PathVariable Long noticeId) {
		noticeService.deleteNotice(noticeId);
	}

	@PostMapping("/api/noticeList")
	public void noticeDeleteList(@RequestBody NoticeDeleteDto noticeDeleteDto) {
		noticeService.deleteNotices(noticeDeleteDto);
	}

	@DeleteMapping("/api/noticeAll")
	public void noticeDeleteAll() {
		noticeService.deleteAllNotices();
	}

	@GetMapping("/api/recentNotice")
	public List<Notice> recentNotice(@RequestParam("size") Integer size) {
		return noticeService.getRecentNotice(size);
	}


}
