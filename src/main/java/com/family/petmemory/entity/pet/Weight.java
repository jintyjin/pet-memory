package com.family.petmemory.entity.pet;

import lombok.Getter;

import javax.persistence.*;
import java.time.LocalDate;

@Entity
@Getter
public class Weight {

    @Id @GeneratedValue
    @Column(name = "weight_id")
    private Long id;

    private Float weight;

    private LocalDate date;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "pet_id")
    private Pet pet;

    protected Weight() {
    }

    public Weight(Float weight, LocalDate date, Pet pet) {
        this.weight = weight;
        this.date = date;
        this.pet = pet;
        this.pet.addWeight(this);
    }
}
