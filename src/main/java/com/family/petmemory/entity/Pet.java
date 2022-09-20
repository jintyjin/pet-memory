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

    @ManyToOne
    @JoinColumn(name = "member_id")
    private Member member;

    @OneToMany(mappedBy = "pet")
    private List<Image> images = new ArrayList<>();

    @Embedded
    private TogetherTime togetherTime;

    private boolean isLeave;

    protected Pet() {
    }

    public Pet(String name, Member member, LocalDateTime bornTime) {
        this.name = name;
        this.member = member;
        this.togetherTime = new TogetherTime(bornTime);
        this.isLeave = false;
    }

    public void leave(LocalDateTime leaveTime) {
        this.togetherTime.leave(leaveTime);
        this.isLeave = true;
    }
}
