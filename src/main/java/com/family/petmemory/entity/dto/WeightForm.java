package com.family.petmemory.entity.dto;

import com.querydsl.core.annotations.QueryProjection;
import lombok.Data;
import org.springframework.format.annotation.DateTimeFormat;

import javax.validation.constraints.DecimalMin;
import javax.validation.constraints.Digits;
import javax.validation.constraints.NotNull;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;

@Data
public class WeightForm {

    @NotNull
    private final Long id;

    private final String petName;

    @DateTimeFormat(pattern = "yyyy-MM-dd")
    @NotNull
    private final LocalDate date;

    @DecimalMin(value = "0.00", inclusive = false)
    @Digits(integer=2, fraction=2)
    private final BigDecimal weight;

    private final List<WeightDto> weightDtoList;
}
