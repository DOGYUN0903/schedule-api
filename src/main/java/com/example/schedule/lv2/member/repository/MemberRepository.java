package com.example.schedule.lv2.member.repository;

import com.example.schedule.global.exception.member.EmailNotFoundException;
import com.example.schedule.global.exception.member.MemberNotFoundException;
import com.example.schedule.lv2.member.entity.Member;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


@Repository
public interface MemberRepository extends JpaRepository<Member, Long> {

    boolean existsByEmail(String email);

    Member findByEmail(String email);

}
