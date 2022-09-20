package com.family.petmemory.entity;

import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Entity
@Getter
public class Member {

    @Id @GeneratedValue
    @Column(name = "member_id")
    private Long id;

    private String name;

    @Embedded
    private MemberTime memberTime;

    @OneToMany(mappedBy = "member")
    private List<Pet> pets = new ArrayList<>();

    private boolean isDelete;

    protected Member() {
    }

    public Member(String name) {
        this.name = name;
        this.memberTime = new MemberTime(LocalDateTime.now());
        this.isDelete = false;
    }

    public void delete() {
        this.memberTime.delete(LocalDateTime.now());
        this.isDelete = true;
    }
}
