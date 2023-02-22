package com.family.petmemory.entity.memory;

import lombok.Getter;

import javax.persistence.Embeddable;

@Embeddable
@Getter
public class Gps {

    private Double Latitude;
    private Double Longitude;

    protected Gps() {
    }

    public Gps(Double Latitude, Double Longitude) {
        this.Latitude = Latitude;
        this.Longitude = Longitude;
    }
}
