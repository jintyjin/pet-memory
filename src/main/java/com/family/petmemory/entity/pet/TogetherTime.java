package com.family.petmemory.entity.pet;

import lombok.Getter;

import javax.persistence.Embeddable;
import java.time.LocalDate;
import java.time.LocalDateTime;

@Embeddable
@Getter
public class TogetherTime {

    private LocalDate bornTime;
    private LocalDate leaveTime;

    protected TogetherTime() {
    }

    public TogetherTime(LocalDate bornTime) {
        this.bornTime = bornTime;
    }

    public void leave(LocalDate leaveTime) {
        this.leaveTime = leaveTime;
    }
}
