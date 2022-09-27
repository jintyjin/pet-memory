package com.family.petmemory.entity.dto;

import lombok.Data;

import javax.validation.constraints.*;
import java.time.LocalDateTime;

@Data
public class MemberForm {

    @NotEmpty
    @Size(min = 8, max = 16)
    private String loginId;

    @Pattern(regexp = "^[ㄱ-ㅎ가-힣]$")
    private String name;

    @NotEmpty
    @Pattern(regexp = "^(?=.*[A-Za-z])(?=.*[0-9])(?=.*[!@#$%^&*])[A-Za-z[0-9]!@#$%^&*]{10,20}$")
    private String password;

    @NotEmpty
    @Email
    private String email;

    @NotNull
    private LocalDateTime birth;
}
