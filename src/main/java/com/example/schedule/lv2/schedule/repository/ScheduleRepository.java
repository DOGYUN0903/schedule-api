package com.example.schedule.lv2.schedule.repository;

import com.example.schedule.global.exception.schedule.ScheduleNotFoundException;
import com.example.schedule.lv2.schedule.entity.Schedule;
import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Repository;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

@Repository
public interface ScheduleRepository extends JpaRepository<Schedule, Long> {

    @Query("SELECT s FROM Schedule s JOIN FETCH s.member")
    List<Schedule> findAllWithMember();

    List<Schedule> findByMemberId(Long memberId);
    
}
