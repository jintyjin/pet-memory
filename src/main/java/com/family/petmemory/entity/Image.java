package com.family.petmemory.entity;

import java.time.LocalDateTime;

public class Image {

    private Long id;
    private String path;
    private LocalDateTime uploadTime;
    private LocalDateTime deleteTime;
    private boolean isDelete;

    public Image(Long id, String path) {
        this.id = id;
        this.path = path;
        this.uploadTime = LocalDateTime.now();
        this.isDelete = false;
    }

    public Image(String path) {
        this.path = path;
        this.uploadTime = LocalDateTime.now();
        this.isDelete = false;
    }

    public void delete() {
        this.deleteTime = LocalDateTime.now();
        this.isDelete = true;
    }
}
