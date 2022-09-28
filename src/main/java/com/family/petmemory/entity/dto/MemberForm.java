package com.family.petmemory.entity.dto;

import lombok.Data;
import org.springframework.format.annotation.DateTimeFormat;

import javax.validation.constraints.*;
import java.time.LocalDate;

@Data
public class MemberForm {

    @NotEmpty
    @Size(min = 8, max = 16)
    private String loginId;

    @Pattern(regexp = "^[가-힣]{1,10}$")
    private String name;

    @NotEmpty
    @Pattern(regexp = "^(?=.*[A-Za-z])(?=.*[0-9])(?=.*[!@#$%^&*])[A-Za-z[0-9]!@#$%^&*]{10,20}$")
    private String password;

    @NotEmpty
    @Email
    private String email;

    @NotNull
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private LocalDate birth;
}
