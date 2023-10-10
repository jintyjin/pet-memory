package com.family.petmemory.infra;

import lombok.Getter;

import javax.persistence.Embeddable;

@Embeddable
@Getter
public class ImageSize {

    private Integer width;
    private Integer height;

    protected ImageSize() {
    }

    public ImageSize(Integer width, Integer height) {
        this.width = width;
        this.height = height;
    }
}
