package com.family.petmemory.entity.pet;

import com.family.petmemory.entity.memory.Memory;
import com.family.petmemory.entity.member.Member;
import lombok.Getter;

import javax.persistence.*;
import java.time.LocalDate;
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
    private List<Memory> memories = new ArrayList<>();

    @Embedded
    private TogetherTime togetherTime;

    @Enumerated(EnumType.STRING)
    private PetStatus petStatus;

    private String profile;

    protected Pet() {
    }

    public Pet(String name, Member member, LocalDate bornTime) {
        this.name = name;
        this.member = member;
        this.member.addPet(this);
        this.togetherTime = new TogetherTime(bornTime);
        this.petStatus = PetStatus.NORMAL;
    }

    public void leave(LocalDate leaveTime) {
        this.togetherTime.leave(leaveTime);
        this.petStatus = PetStatus.LEAVE;
    }

    public void addImage(Memory image) {
        this.memories.add(image);
    }

    public void changeProfile(String profile) {
        this.profile = profile;
    }
}
