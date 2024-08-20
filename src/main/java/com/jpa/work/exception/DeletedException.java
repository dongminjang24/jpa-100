package com.jpa.work.exception;

import org.springframework.http.HttpStatus;

import lombok.NoArgsConstructor;

@NoArgsConstructor
public class DeletedException extends AbstractException{

	private String message = "리소스는 이미 삭제되었습니다.";

	public DeletedException(String message) {
		if (message != null) {
			this.message = message;
		}
	}


	@Override
	public int getStatusCode() {
		return HttpStatus.OK.value();
	}

	@Override
	public String getMessage() {
		return message;
	}
}