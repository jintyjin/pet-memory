package com.family.petmemory.service;

import com.family.petmemory.entity.dto.PetForm;
import com.family.petmemory.entity.member.Member;
import com.family.petmemory.entity.pet.Pet;
import com.family.petmemory.repository.member.MemberRepository;
import com.family.petmemory.repository.pet.PetRepository;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Rollback;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.util.List;

import static org.assertj.core.api.Assertions.*;
import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class PetServiceTest {

    @Autowired
    MemberRepository memberRepository;

    @Autowired
    PetService petService;

    @Test
    @Transactional
    @Rollback(value = false)
    void joinTest() {
        //given
        Member member = new Member("memberA", "주인1", "암호", "jin@naver.com", LocalDate.now());
        Long savedId = memberRepository.save(member);
        Member findMember = memberRepository.findById(savedId);

        //when
        petService.join(new PetForm(savedId, "petA", LocalDate.now()));
        petService.join(new PetForm(savedId, "petB", LocalDate.now()));
        petService.join(new PetForm(savedId, "petC", LocalDate.now()));

        //then
        assertThat(member.getId()).isEqualTo(findMember.getId());
        List<Pet> pets = member.getPets();
        for (Pet pet : pets) {
            System.out.println("pet.getName() = " + pet.getName());            
        }
    }
}