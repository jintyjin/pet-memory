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

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "pet_id")
    private Pet pet;

    @Enumerated(EnumType.STRING)
    private memoryStatus memoryStatus;

    protected Memory() {
    }

    public Memory(UploadFile uploadFile, Pet pet) {
        this.uploadFile = uploadFile;
        this.pet = pet;
        this.pet.addImage(this);
        this.manageTime = new ManageTime(LocalDateTime.now());
        this.memoryStatus = memoryStatus.NORMAL;
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
