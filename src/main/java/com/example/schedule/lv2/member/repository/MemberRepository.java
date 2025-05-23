package com.example.schedule.lv2.member.repository;

import com.example.schedule.global.exception.member.EmailNotFoundException;
import com.example.schedule.global.exception.member.MemberNotFoundException;
import com.example.schedule.lv2.member.entity.Member;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


@Repository
public interface MemberRepository extends JpaRepository<Member, Long> {

    default Member findByIdOrElseThrow(Long id) {
        return findById(id)
                .orElseThrow(() ->
                        new MemberNotFoundException("존재하지 않는 회원입니다.")
                );
    }

    boolean existsByEmail(String email);

    Member findByEmail(String email);

    default Member findByEmailOrElseThrow(String email) {
        Member member = findByEmail(email);

        if (member == null) {
            throw new EmailNotFoundException("해당 이메일의 회원이 존재하지 않습니다.");
        }
        return member;
    }

}
