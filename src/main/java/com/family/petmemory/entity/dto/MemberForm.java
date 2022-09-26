package com.family.petmemory.entity.dto;

import lombok.Data;

import javax.validation.constraints.Email;
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
    @Pattern(regexp = "^(?=.*[A-Za-z])(?=.*[0-9])(?=.*[!@#$%^&*])[A-Za-z[0-9]!@#$%^&*]{10,20}$"
            , message = "암호는 숫자, 영문자, 특수문자를 포함한 10~20자리여야 합니다.")
    private String password;

    @NotEmpty(message = "이메일은 빈 칸일 수 없습니다.")
    @Email(message = "잘못된 형식입니다.")
    private String email;
}
