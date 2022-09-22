package com.family.petmemory.entity;

import lombok.Getter;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Entity
@Getter
public class Pet {

    @Id @GeneratedValue
    @Column(name = "pet_id")
    private Long id;

    private String name;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "member_id")
    private Member member;

    @OneToMany(mappedBy = "pet")
    private List<Image> images = new ArrayList<>();

    @Embedded
    private TogetherTime togetherTime;

    private PetStatus petStatus;

    protected Pet() {
    }

    public Pet(String name, Member member, LocalDateTime bornTime) {
        this.name = name;
        this.member = member;
        this.member.addPet(this);
        this.togetherTime = new TogetherTime(bornTime);
        this.petStatus = PetStatus.NORMAL;
    }

    public void leave(LocalDateTime leaveTime) {
        this.togetherTime.leave(leaveTime);
        this.petStatus = PetStatus.LEAVE;
    }
}
