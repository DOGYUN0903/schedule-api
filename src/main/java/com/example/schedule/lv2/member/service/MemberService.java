package com.example.schedule.lv2.member.service;

import com.example.schedule.lv2.member.dto.signup.MemberSignupRequestDto;
import com.example.schedule.lv2.member.dto.signup.MemberSignupResponseDto;
import com.example.schedule.lv2.member.entity.Member;
import com.example.schedule.lv2.member.repository.MemberRepository;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class MemberService {

    private final MemberRepository memberRepository;

    public MemberSignupResponseDto saveMember(MemberSignupRequestDto requestDto) {

        Member member =
                new Member(
                        requestDto.getUsername(),
                        requestDto.getEmail(),
                        requestDto.getPassword()
                );

        Member savedMember = memberRepository.save(member);

        return new MemberSignupResponseDto(savedMember);
    }

    public List<MemberSignupResponseDto> findAll() {
        return memberRepository.findAll().stream()
                .map(MemberSignupResponseDto::new)
                .toList();
    }

    public MemberSignupResponseDto findById(Long id) {

        Member findMember = memberRepository.findByIdOrElseThrow(id);

        return new MemberSignupResponseDto(findMember);
    }
}
