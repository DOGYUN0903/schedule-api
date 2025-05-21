package com.example.schedule.lv2.member.controller;

import com.example.schedule.lv2.member.dto.signup.MemberSignupRequestDto;
import com.example.schedule.lv2.member.dto.signup.MemberSignupResponseDto;
import com.example.schedule.lv2.member.dto.update.MemberUpdateRequestDto;
import com.example.schedule.lv2.member.service.MemberService;
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
                                             @RequestParam("oldPassword") String oldPassword,
                                             @Valid @RequestBody MemberUpdateRequestDto updateRequestDto) {
        memberService.updateMember(id, oldPassword, updateRequestDto);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteMember(@PathVariable("id") Long id,
                                             @RequestParam("password") String password) {
        memberService.deleteMember(id, password);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}
