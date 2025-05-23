package com.example.schedule.lv2.comment.dto;

import com.example.schedule.lv2.comment.entity.Comment;
import lombok.Getter;

import java.time.LocalDateTime;

@Getter
public class CommentResponseDto {

    private Long id;

    private String username;

    private String content;

    private LocalDateTime modifiedAt;

    public CommentResponseDto(Comment comment) {
        this.id = comment.getId();
        this.username = comment.getMember().getUsername();
        this.content = comment.getContent();
        this.modifiedAt = comment.getModifiedAt();
    }
}
