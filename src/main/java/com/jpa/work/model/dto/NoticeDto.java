package com.jpa.work.model.dto;


import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class NoticeDto {

	@NotBlank(message = "제목을 입력해주세요.")
	@Size(min = 10,max = 100, message = "제목은 10-100 이상 작성해주세요.")
	private String title;

	@NotBlank(message = "내용을 입력해주세요.")
	@Size(min = 50,max = 1000, message = "내용은 50-1000 이상 작성해주세요.")
	private String content;
}
