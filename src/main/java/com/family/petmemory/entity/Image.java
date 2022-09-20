package com.family.petmemory.entity;

import lombok.Getter;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
@Getter
public class Image {

    @Id @GeneratedValue
    @Column(name = "image_id")
    private Long id;

    private String path;

    @Embedded
    private ManageTime manageTime;

    @ManyToOne
    @JoinColumn(name = "pet_id")
    private Pet pet;

    private boolean isDelete;

    protected Image() {
    }

    public Image(String path) {
        this.path = path;
        this.manageTime = new ManageTime(LocalDateTime.now());
        this.isDelete = false;
    }

    public void delete() {
        this.manageTime.delete(LocalDateTime.now());
        this.isDelete = true;
    }
}
