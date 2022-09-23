package com.family.petmemory.entity.image;

import lombok.Getter;

import javax.persistence.Embeddable;
import java.time.LocalDateTime;

@Embeddable
@Getter
public class ManageTime {

    private LocalDateTime uploadTime;
    private LocalDateTime deleteTime;

    protected ManageTime() {
    }

    public ManageTime(LocalDateTime uploadTime) {
        this.uploadTime = uploadTime;
    }

    public void delete(LocalDateTime deleteTime) {
        this.deleteTime = deleteTime;
    }
}
