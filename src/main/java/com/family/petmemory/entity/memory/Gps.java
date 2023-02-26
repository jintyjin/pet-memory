package com.family.petmemory.entity.memory;

import lombok.Getter;

import javax.persistence.Embeddable;

@Embeddable
@Getter
public class Gps {

    private Double latitude;
    private Double longitude;

    protected Gps() {
    }

    public Gps(Double latitude, Double longitude) {
        this.latitude = latitude;
        this.longitude = longitude;
    }
}
