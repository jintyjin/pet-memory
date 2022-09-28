package com.family.petmemory.entity.member;

import com.family.petmemory.entity.pet.Pet;
import lombok.Getter;

import javax.persistence.*;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Entity
@Getter
public class Member {

    @Id @GeneratedValue
    @Column(name = "member_id")
    private Long id;

    @Column(name = "login_id", unique = true)
    private String loginId;

    private String password;

    private String name;

    @Embedded
    private MemberTime memberTime;

    @OneToMany(mappedBy = "member")
    private List<Pet> pets = new ArrayList<>();

    @Enumerated(EnumType.STRING)
    private MemberStatus memberStatus;

    private String email;

    private LocalDate birth;

    protected Member() {
    }

    public Member(String loginId, String name, String password, String email, LocalDate birth) {
        this.loginId = loginId;
        this.name = name;
        this.password = password;
        this.memberTime = new MemberTime(LocalDateTime.now());
        this.memberStatus = MemberStatus.NORMAL;
        this.email = email;
        this.birth = birth;
    }

    public void delete() {
        this.memberTime.delete(LocalDateTime.now());
        this.memberStatus = MemberStatus.DELETE;
    }

    public void addPet(Pet pet) {
        this.pets.add(pet);
    }
}
