package com.family.petmemory.entity.image;

import com.family.petmemory.entity.pet.Pet;
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

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "pet_id")
    private Pet pet;

    @Enumerated(EnumType.STRING)
    private ImageStatus imageStatus;

    protected Image() {
    }

    public Image(String path, Pet pet) {
        this.path = path;
        this.pet = pet;
        this.pet.addImage(this);
        this.manageTime = new ManageTime(LocalDateTime.now());
        this.imageStatus = ImageStatus.NORMAL;
    }

    public void delete() {
        this.manageTime.delete(LocalDateTime.now());
        this.imageStatus = ImageStatus.DELETE;
    }
}