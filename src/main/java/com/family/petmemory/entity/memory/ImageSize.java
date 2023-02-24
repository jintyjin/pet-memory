package com.family.petmemory.entity.memory;

import lombok.Getter;

import javax.persistence.Embeddable;

@Embeddable
@Getter
public class ImageSize {

    private int width;
    private int height;

    protected ImageSize() {
    }

    public ImageSize(int width, int height) {
        this.width = width;
        this.height = height;
    }
}
