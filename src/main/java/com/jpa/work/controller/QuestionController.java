package com.jpa.work.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@RestController
@RequestMapping
public class QuestionController {

	@GetMapping("/first-url")
	public void firstUrl() {
		log.info("firstUrl() 호출됨");
	}
}
