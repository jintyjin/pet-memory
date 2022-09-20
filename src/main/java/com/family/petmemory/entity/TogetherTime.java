package com.family.petmemory.entity;

import lombok.Getter;

import javax.persistence.Embeddable;
import java.time.LocalDateTime;

@Embeddable
@Getter
public class TogetherTime {

    private LocalDateTime bornTime;
    private LocalDateTime leaveTime;

    protected TogetherTime() {
    }

    public TogetherTime(LocalDateTime bornTime) {
        this.bornTime = bornTime;
    }

    public void leave(LocalDateTime leaveTime) {
        this.leaveTime = leaveTime;
    }
}
