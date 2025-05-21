package com.example.schedule.lv2.member.controller;

import com.example.schedule.lv2.member.dto.signup.MemberSignupRequestDto;
import com.example.schedule.lv2.member.dto.signup.MemberSignupResponseDto;
import com.example.schedule.lv2.member.service.MemberService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v2/members")
public class MemberController {

    private final MemberService memberService;

    @PostMapping
    private ResponseEntity<MemberSignupResponseDto> saveMember(@Valid @RequestBody MemberSignupRequestDto requestDto) {
        return new ResponseEntity<>(memberService.saveMember(requestDto), HttpStatus.CREATED);
    }
}
