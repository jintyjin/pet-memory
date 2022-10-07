package com.family.petmemory.entity.dto;

import lombok.Data;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.web.multipart.MultipartFile;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import java.time.LocalDate;

@Data
public class PetForm {

    @NotNull
    private Long memberId;

    @Pattern(regexp = "^[가-힣]{1,10}$")
    private String name;

    @NotNull
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private LocalDate bornTime;

    private MultipartFile multipartFile;

    protected PetForm() {
    }

    public PetForm(Long memberId) {
        this.memberId = memberId;
    }

    public PetForm(Long memberId, String name, LocalDate bornTime, MultipartFile multipartFile) {
        this.memberId = memberId;
        this.name = name;
        this.bornTime = bornTime;
        this.multipartFile = multipartFile;
    }
}
