package com.example.schedule.lv1.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.Getter;

@Getter
public class ScheduleRequestDto {

    @NotBlank(message = "제목은 필수입니다.")
    @Size(min = 2, max = 50, message = "제목은 2자 이상 50자 이하로 입력해주세요.")
    private String title;

    @NotBlank(message = "내용은 필수입니다.")
    private String contents;
}
