package com.jpa.work.exception;

import org.springframework.http.HttpStatus;

import lombok.NoArgsConstructor;

@NoArgsConstructor
public class NotFound extends AbstractException{

	private String message = "리소스를 찾을 수 없습니다.";

	public NotFound(String message) {
		if (message != null) {
			this.message = message;
		}
	}


	@Override
	public int getStatusCode() {
		return HttpStatus.BAD_REQUEST.value();
	}

	@Override
	public String getMessage() {
		return message;
	}
}