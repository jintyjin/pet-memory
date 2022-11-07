package com.family.petmemory.entity.dto;

import com.querydsl.core.annotations.QueryProjection;
import lombok.AllArgsConstructor;
import lombok.Data;

import java.time.LocalDate;

@Data
public class WeightForm {

    private Long id;

    private LocalDate date;

    private Float weight;

    @QueryProjection
    public WeightForm(Long id, LocalDate date, Float weight) {
        this.id = id;
        this.date = date;
        this.weight = weight;
    }
}
