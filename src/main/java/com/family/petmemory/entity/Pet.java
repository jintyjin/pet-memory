package com.family.petmemory.entity;

import java.time.LocalDateTime;

public class Pet {

    private Long id;
    private String name;
    private Long memberId;
    private LocalDateTime bornTime;
    private LocalDateTime leaveTime;
    private boolean isLeave;

    public Pet(Long id, String name, Long memberId, LocalDateTime bornTime) {
        this.id = id;
        this.name = name;
        this.memberId = memberId;
        this.bornTime = bornTime;
        this.isLeave = false;
    }

    public Pet(String name, Long memberId, LocalDateTime bornTime) {
        this.name = name;
        this.memberId = memberId;
        this.bornTime = bornTime;
        this.isLeave = false;
    }

    public void leave(LocalDateTime leaveTime) {
        this.leaveTime = leaveTime;
        this.isLeave = true;
    }
}
