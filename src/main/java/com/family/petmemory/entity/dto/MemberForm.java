package com.family.petmemory.entity.dto;

import lombok.Data;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

@Data
public class MemberForm {

    @NotEmpty(message = "아이디는 빈 칸일 수 없습니다.")
    @Size(min = 8, max = 16, message = "아이디는 8글자 이상 16글자 이하입니다.")
    private String loginId;

    @NotEmpty(message = "이름은 빈 칸일 수 없습니다.")
    @Pattern(regexp = "^[ㄱ-ㅎ가-힣]$", message = "이름은 한글만 가능합니다.")
    private String name;

    @NotEmpty(message = "암호는 빈 칸일 수 없습니다.")
    @Size(min = 10, max = 20, message = "암호는 10글자 이상 20글자 이하입니다.")
    @Pattern(regexp = "^(?=.*[A-Za-z])(?=.*[0-9])(?=.*[!@#$%^&*])[A-Za-z[0-9]!@#$%^&*]"
            , message = "숫자, 영문자, 특수문자를 포함한 비밀번호를 입력해주세요.")
    private String password;
}
