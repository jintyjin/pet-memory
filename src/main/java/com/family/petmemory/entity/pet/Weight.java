package com.family.petmemory.entity.pet;

import lombok.Getter;

import javax.persistence.*;
import java.math.BigDecimal;
import java.time.LocalDate;

@Entity
@Getter
public class Weight {

    @Id @GeneratedValue
    @Column(name = "weight_id")
    private Long id;

    @Column(scale = 2)
    private BigDecimal weight;

    private LocalDate date;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "pet_id")
    private Pet pet;

    protected Weight() {
    }

    public Weight(BigDecimal weight, LocalDate date, Pet pet) {
        this.weight = weight;
        this.date = date;
        this.pet = pet;
        this.pet.addWeight(this);
    }
}
