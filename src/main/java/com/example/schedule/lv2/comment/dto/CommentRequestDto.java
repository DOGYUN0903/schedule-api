package com.example.schedule.lv2.comment.dto;

import jakarta.validation.constraints.NotBlank;
import lombok.Getter;

@Getter
public class CommentRequestDto {

    @NotBlank(message = "댓글 내용은 비어 있을 수 없습니다.")
    private String content;
}
