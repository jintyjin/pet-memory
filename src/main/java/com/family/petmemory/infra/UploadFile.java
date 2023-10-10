package com.family.petmemory.entity.memory;

import lombok.AllArgsConstructor;
import lombok.Getter;

import javax.persistence.Embeddable;

@Embeddable
@AllArgsConstructor
@Getter
public class UploadFile {

    private String uploadFileName;
    private String saveFileName;

    protected UploadFile() {
    }
}
