package com.jpa.work.service;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import org.springframework.data.domain.Limit;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.validation.Errors;

import com.jpa.work.exception.DeletedException;
import com.jpa.work.exception.ErrorResponse;
import com.jpa.work.exception.NotFound;
import com.jpa.work.model.Notice;
import com.jpa.work.model.dto.NoticeDeleteDto;
import com.jpa.work.model.dto.NoticeDto;
import com.jpa.work.repository.NoticeRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class NoticeService {

	private final NoticeRepository noticeRepository;

	@Transactional
	public ResponseEntity<?> register(NoticeDto noticeDto, Errors errors) {

		if (errors.hasErrors()) {
			ArrayList<ErrorResponse> arrayList = new ArrayList<>();
			errors.getAllErrors().forEach(error -> {
				ErrorResponse errorResponse = new ErrorResponse();
				errorResponse.setMessage(error.getDefaultMessage());
				errorResponse.setCode(400);
				arrayList.add(errorResponse);
			});
			return new ResponseEntity<>(arrayList, HttpStatus.BAD_REQUEST);
		} else {
			Notice notice = Notice.builder()
				.title(noticeDto.getTitle())
				.content(noticeDto.getContent())
				.regDate(LocalDateTime.now())
				.build();
			LocalDateTime localDateTime = LocalDateTime.now().minusMinutes(1);
			List<Notice> byTitleAndContentAndRegDateIsGreaterThanEqual = noticeRepository.findByTitleAndContentAndRegDateIsGreaterThanEqual(
				noticeDto.getTitle(), noticeDto.getContent(), localDateTime);
			if (!byTitleAndContentAndRegDateIsGreaterThanEqual.isEmpty()) {
				throw new DeletedException("이미 등록된 내용입니다.");
			}
			return ResponseEntity.ok(noticeRepository.save(notice));

		}
	}

	public Notice getDetail(Long id) {
		Notice notice = noticeRepository.findById(id).orElseThrow(
			NotFound::new);

		notice.addHits();
		if (notice.getDeleteTime()!=null) {
			throw new NotFound("이미 삭제된 내용입니다.");
		}
		noticeRepository.save(notice);
		return notice;
	}

	@Transactional
	public Notice editNotice(Long id, NoticeDto noticeDto) {
		Notice notice = noticeRepository.findById(id).orElseThrow(
			NotFound::new);

		notice.changeTitle(noticeDto.getTitle());
		notice.changeContent(noticeDto.getContent());

		noticeRepository.save(notice);
		return notice;
	}


	@Transactional
	public void deleteNotice(Long id) {
		Notice notice = noticeRepository.findById(id).orElseThrow(
			()-> new NotFound("내용이 존재하지 않습니다."));

		if (notice.getDeleteTime()!=null) {
			throw new DeletedException("이미 삭제된 글입니다.");
		}
		notice.updateDeleteTimeExists();
		noticeRepository.save(notice);
	}

	@Transactional
	public void deleteNotices(NoticeDeleteDto noticeDto) {
		noticeRepository.findByIdIn(noticeDto.getIds()).orElseThrow(
			()-> new NotFound("내용이 존재하지 않습니다."));

		noticeDto.getIds().forEach(id -> {
			Notice notice = noticeRepository.findById(id).orElseThrow(
				()-> new NotFound("내용이 존재하지 않습니다."));

			if (notice.getDeleteTime()!=null) {
				throw new DeletedException("이미 삭제된 글입니다.");
			}
			notice.updateDeleteTimeExists();
			noticeRepository.save(notice);
		});
	}

	@Transactional
	public void deleteAllNotices() {
		noticeRepository.deleteAll();
	}

	public List<Notice> getRecentNotice(Integer size) {
		return noticeRepository.findByOrderByCreateAtDesc(Limit.of(size));
	}


}
