package com.family.petmemory.entity;

import lombok.Getter;

import javax.persistence.Embeddable;
import java.time.LocalDateTime;

@Embeddable
@Getter
public class MemberTime {

    private LocalDateTime joinTime;
    private LocalDateTime deleteTime;

    protected MemberTime() {
    }

    public MemberTime(LocalDateTime joinTime) {
        this.joinTime = joinTime;
    }

    public void delete(LocalDateTime deleteTime) {
        this.deleteTime = deleteTime;
    }
}
