package com.example.schedule.lv2.member.dto.update;

import jakarta.validation.constraints.AssertTrue;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.Getter;

@Getter
public class MemberUpdateRequestDto {

    @Size(min = 2, max = 10, message = "사용자명은 2자 이상 10자 이하여야 합니다.")
    private String username;

    @Pattern(regexp = "(?=.*[0-9])(?=.*[a-zA-Z])(?=.*\\W)(?=\\S+$).{8,16}",
            message = "비밀번호는 8~16자 영문 대 소문자, 숫자, 특수문자를 사용하세요.")
    private String password;

    @NotBlank(message = "기존 비밀번호는 필수입니다.")
    private String oldPassword;

    @AssertTrue(message = "수정할 항목을 최소 1개 이상 입력해야 합니다.")
    public boolean isAnyFieldUpdated() {
        return username != null || password != null;
    }
}
