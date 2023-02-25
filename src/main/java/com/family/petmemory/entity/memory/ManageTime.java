package com.family.petmemory.entity.memory;

import lombok.Getter;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import javax.persistence.Embeddable;
import java.time.LocalDateTime;

@Embeddable
@Getter
public class ManageTime {

    private LocalDateTime uploadTime = LocalDateTime.now();

    private LocalDateTime updateTime = LocalDateTime.now();

    private LocalDateTime imageTime;

    private LocalDateTime deleteTime;

    protected ManageTime() {
    }

    public ManageTime(LocalDateTime imageTime) {
        this.imageTime = imageTime;
    }

    public void update(LocalDateTime updateTime) {
        this.updateTime = updateTime;
    }

    public void delete(LocalDateTime deleteTime) {
        this.deleteTime = deleteTime;
    }
}
