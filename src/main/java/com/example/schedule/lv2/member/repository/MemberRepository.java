package com.example.schedule.lv2.member.repository;

import com.example.schedule.lv2.member.entity.Member;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Repository;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;
import java.util.Optional;

@Repository
public interface MemberRepository extends JpaRepository<Member, Long> {

    default Member findByIdOrElseThrow(Long id) {
        return findById(id)
                .orElseThrow(() ->
                        new ResponseStatusException(
                                HttpStatus.NOT_FOUND,
                                "Does not exist id = " + id
                        )
                );
    }

    boolean existsByEmail(String email);

    Member findByEmail(String email);

    default Member findByEmailOrElseThrow(String email) {
        Member member = findByEmail(email);

        if (member == null) {
            throw new ResponseStatusException(HttpStatus.UNAUTHORIZED, "해당 이메일의 회원이 존재하지 않습니다.");
        }
        return member;
    }

}
