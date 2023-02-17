package com.family.petmemory.entity.memory;

import lombok.Getter;

import javax.persistence.Embeddable;
import java.time.LocalDateTime;

@Embeddable
@Getter
public class ManageTime {

    private LocalDateTime uploadTime;
    private LocalDateTime imageTime;
    private LocalDateTime deleteTime;

    protected ManageTime() {
    }

    public ManageTime(LocalDateTime uploadTime, LocalDateTime imageTime) {
        this.uploadTime = uploadTime;
        this.imageTime = imageTime;
    }

    public void delete(LocalDateTime deleteTime) {
        this.deleteTime = deleteTime;
    }
}
