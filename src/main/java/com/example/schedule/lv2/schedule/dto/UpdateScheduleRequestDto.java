package com.example.schedule.lv2.schedule.dto;

import jakarta.validation.constraints.AssertTrue;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.Getter;

@Getter
public class UpdateScheduleRequestDto {

    @Size(min = 2, max = 50, message = "제목은 2자 이상 50자 이하로 입력해주세요.")
    private String title;

    private String contents;

    private String password;

    @AssertTrue(message = "수정할 항목을 최소 1개 이상 입력해야 합니다.")
    public boolean isAnyFieldUpdated() {
        return title != null || contents != null;
    }
}
