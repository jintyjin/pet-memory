package com.family.petmemory.entity.memory;

import lombok.Getter;

import javax.persistence.Embeddable;

@Embeddable
@Getter
public class Gps {

    private Double x;
    private Double y;

    protected Gps() {
    }

    public Gps(Double x, Double y) {
        this.x = x;
        this.y = y;
    }
}
