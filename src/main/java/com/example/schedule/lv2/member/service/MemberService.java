package com.example.schedule.lv2.member.service;

import com.example.schedule.global.auth.PasswordEncoder;
import com.example.schedule.global.exception.common.UnauthorizedAccessException;
import com.example.schedule.global.exception.member.EmailAlreadyExistsException;
import com.example.schedule.global.exception.common.InvalidPasswordException;
import com.example.schedule.global.exception.member.EmailNotFoundException;
import com.example.schedule.global.exception.member.MemberNotFoundException;
import com.example.schedule.lv2.member.dto.DeleteMemberRequestDto;
import com.example.schedule.lv2.member.dto.login.LoginRequestDto;
import com.example.schedule.lv2.member.dto.signup.MemberSignupRequestDto;
import com.example.schedule.lv2.member.dto.signup.MemberSignupResponseDto;
import com.example.schedule.lv2.member.dto.update.MemberUpdateRequestDto;
import com.example.schedule.lv2.member.entity.Member;
import com.example.schedule.lv2.member.repository.MemberRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
public class MemberService {

    private final MemberRepository memberRepository;
    private final PasswordEncoder passwordEncoder;

    public MemberSignupResponseDto saveMember(MemberSignupRequestDto requestDto) {

        String encodedPassword = passwordEncoder.encode(requestDto.getPassword());

        if (memberRepository.existsByEmail(requestDto.getEmail())) {
            throw new EmailAlreadyExistsException("이미 사용 중인 이메일입니다.");
        }

        Member member =
                new Member(
                        requestDto.getUsername(),
                        requestDto.getEmail(),
                        encodedPassword
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
        Member findMember = memberRepository.findById(id)
                .orElseThrow(() -> new MemberNotFoundException("존재하지 않는 회원입니다."));

        return new MemberSignupResponseDto(findMember);
    }

    @Transactional
    public void updateMember(Long id, MemberUpdateRequestDto updateRequestDto, Long loginMemberId) {
        Member findMember = memberRepository.findById(id)
                .orElseThrow(() -> new MemberNotFoundException("존재하지 않는 회원입니다."));

        if (!findMember.getId().equals(loginMemberId)) {
            throw new UnauthorizedAccessException("작성자만 본인만 수정할 수 있습니다.");
        }

        if (!passwordEncoder.matches(updateRequestDto.getOldPassword(), findMember.getPassword())) {
            throw new InvalidPasswordException("비밀번호가 일치하지 않습니다.");
        }

        findMember.update(updateRequestDto);
    }

    public void deleteMember(Long id, String password, Long loginMemberId) {
        Member findMember = memberRepository.findById(id)
                .orElseThrow(() -> new MemberNotFoundException("존재하지 않는 회원입니다."));

        if (!findMember.getId().equals(loginMemberId)) {
            throw new UnauthorizedAccessException("작성자만 본인만 삭제할 수 있습니다.");
        }

        if (!passwordEncoder.matches(password, findMember.getPassword())) {
            throw new InvalidPasswordException("비밀번호가 일치하지 않습니다.");
        }

        memberRepository.delete(findMember);
    }


    public Member login(LoginRequestDto requestDto) {

        Member findMember = memberRepository.findByEmail(requestDto.getEmail());
        if (findMember == null) {
            throw new EmailNotFoundException("해당 이메일의 회원이 존재하지 않습니다.");
        }

        if (!passwordEncoder.matches(requestDto.getPassword(), findMember.getPassword())) {
            throw new InvalidPasswordException("비밀번호가 일치하지 않습니다.");
        }

        return findMember;
    }
}
