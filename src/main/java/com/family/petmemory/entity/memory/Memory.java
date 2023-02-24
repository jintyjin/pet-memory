package com.family.petmemory.entity.memory;

import com.family.petmemory.entity.pet.Pet;
import lombok.Getter;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
@Getter
public class Memory {

    @Id @GeneratedValue
    @Column(name = "memory_id")
    private Long id;

    @Embedded
    private UploadFile uploadFile;

    @Embedded
    private ManageTime manageTime;

    @Embedded
    private ImageSize imageSize;

    @Embedded
    private Gps gps;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "pet_id")
    private Pet pet;

    @Enumerated(EnumType.STRING)
    private MemoryStatus memoryStatus;

    @Enumerated(EnumType.STRING)
    private MemoryType memoryType;

    private String info;

    protected Memory() {
    }

    public Memory(UploadFile uploadFile, LocalDateTime imageTime, ImageSize imageSize, Gps gps, Pet pet, MemoryType memoryType) {
        this.uploadFile = uploadFile;
        this.manageTime = new ManageTime(LocalDateTime.now(), imageTime);
        this.imageSize = imageSize;
        this.gps = gps;
        this.pet = pet;
        this.pet.addImage(this);
        this.memoryStatus = memoryStatus.NORMAL;
        this.memoryType = memoryType;
        this.info = "";
    }

    public void delete() {
        this.manageTime.delete(LocalDateTime.now());
        this.memoryStatus = memoryStatus.DELETE;
        if (isProfile()) {
            this.pet.changeProfile(null);
        }
    }

    private boolean isProfile() {
        return this.pet.getProfile() != null && this.pet.getProfile().equals(this.getUploadFile().getSaveFileName());
    }
}
