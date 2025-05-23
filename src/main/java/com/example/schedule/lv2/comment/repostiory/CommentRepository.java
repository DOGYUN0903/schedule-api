package com.example.schedule.lv2.comment.repostiory;

import com.example.schedule.lv2.comment.entity.Comment;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CommentRepository extends JpaRepository<Comment, Long> {

    List<Comment> findAllCommentsByScheduleId(Long scheduleId);
}
