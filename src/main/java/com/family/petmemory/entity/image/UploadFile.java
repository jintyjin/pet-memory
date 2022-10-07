package com.family.petmemory.entity.image;

import lombok.AllArgsConstructor;
import lombok.Getter;

import javax.persistence.Embeddable;

@Embeddable
@AllArgsConstructor
@Getter
public class UploadFile {

    private String uploadFileName;
    private String storeFileName;

    protected UploadFile() {
    }
}
