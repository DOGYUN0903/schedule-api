package com.example.schedule.lv2.comment.dto;

import jakarta.validation.constraints.NotBlank;
import lombok.Getter;

@Getter
public class UpdateCommentRequestDto {

    @NotBlank(message = "수정할 내용을 입력해주세요.")
    private String content;
}
