package com.family.petmemory.entity.dto;

import com.querydsl.core.annotations.QueryProjection;
import lombok.Data;
import org.springframework.format.annotation.DateTimeFormat;

import javax.validation.constraints.DecimalMin;
import javax.validation.constraints.Digits;
import javax.validation.constraints.NotNull;
import java.math.BigDecimal;
import java.time.LocalDate;

@Data
public class WeightDto {

    @NotNull
    private Long id;

    @DateTimeFormat(pattern = "yyyy-MM-dd")
    @NotNull
    private LocalDate date;

    @DecimalMin(value = "0.00", inclusive = false)
    @Digits(integer=2, fraction=2)
    private BigDecimal weight;

    @QueryProjection
    public WeightDto(Long id, LocalDate date, BigDecimal weight) {
        this.id = id;
        this.date = date;
        this.weight = weight;
    }
}
