package com.example.schedule.lv2.member.controller;

import com.example.schedule.global.session.SessionConst;
import com.example.schedule.lv2.member.dto.DeleteMemberRequestDto;
import com.example.schedule.lv2.member.dto.login.LoginRequestDto;
import com.example.schedule.lv2.member.dto.login.LoginResponseDto;
import com.example.schedule.lv2.member.dto.signup.MemberSignupRequestDto;
import com.example.schedule.lv2.member.dto.signup.MemberSignupResponseDto;
import com.example.schedule.lv2.member.dto.update.MemberUpdateRequestDto;
import com.example.schedule.lv2.member.entity.Member;
import com.example.schedule.lv2.member.service.MemberService;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v2/members")
public class MemberController {

    private final MemberService memberService;

    @PostMapping
    public ResponseEntity<MemberSignupResponseDto> saveMember(@Valid @RequestBody MemberSignupRequestDto requestDto) {
        return new ResponseEntity<>(memberService.saveMember(requestDto), HttpStatus.CREATED);
    }

    @GetMapping
    public ResponseEntity<List<MemberSignupResponseDto>> findAll() {
        return new ResponseEntity<>(memberService.findAll(), HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<MemberSignupResponseDto> findById(@PathVariable("id") Long id) {
        return new ResponseEntity<>(memberService.findById(id), HttpStatus.OK);
    }

    @PatchMapping("/{id}")
    public ResponseEntity<Void> updateMember(@PathVariable("id") Long id,
                                             @Valid @RequestBody MemberUpdateRequestDto updateRequestDto) {
        memberService.updateMember(id, updateRequestDto);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteMember(@PathVariable("id") Long id,
                                             @Valid @RequestBody DeleteMemberRequestDto requestDto) {
        memberService.deleteMember(id, requestDto.getPassword());
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @PostMapping("/login")
    public ResponseEntity<LoginResponseDto> login(@Valid @RequestBody LoginRequestDto requestDto,
                                                  HttpServletRequest request) {
        Member member = memberService.login(requestDto);

        HttpSession session = request.getSession();
        session.setAttribute(SessionConst.LOGIN_MEMBER, member.getId());

        return new ResponseEntity<>(new LoginResponseDto(
                member.getId(),
                member.getUsername(),
                member.getEmail()
        ),
                HttpStatus.OK
        );
    }
}
