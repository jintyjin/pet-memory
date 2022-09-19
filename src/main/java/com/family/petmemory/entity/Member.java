package com.family.petmemory.entity;

import java.time.LocalDateTime;

public class Member {

    private Long id;
    private String name;
    private LocalDateTime joinTime;
    private LocalDateTime deleteTime;
    private boolean isDelete;

    public Member(Long id, String name) {
        this.id = id;
        this.name = name;
        this.joinTime = LocalDateTime.now();
        this.isDelete = false;
    }

    public Member(String name) {
        this.name = name;
        this.joinTime = LocalDateTime.now();
        this.isDelete = false;
    }

    public void delete() {
        this.deleteTime = LocalDateTime.now();
        this.isDelete = true;
    }
}
