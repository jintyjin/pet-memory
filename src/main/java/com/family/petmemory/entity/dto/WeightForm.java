package com.family.petmemory.entity.dto;

import com.querydsl.core.annotations.QueryProjection;
import lombok.AllArgsConstructor;
import lombok.Data;

import java.math.BigDecimal;
import java.time.LocalDate;

@Data
public class WeightForm {

    private Long id;

    private LocalDate date;

    private BigDecimal weight;

    @QueryProjection
    public WeightForm(Long id, LocalDate date, BigDecimal weight) {
        this.id = id;
        this.date = date;
        this.weight = weight;
    }
}
