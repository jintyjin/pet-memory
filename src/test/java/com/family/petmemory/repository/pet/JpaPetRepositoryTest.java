package com.family.petmemory.repository.pet;

import com.family.petmemory.entity.member.Member;
import com.family.petmemory.entity.pet.Pet;
import com.family.petmemory.repository.member.MemberRepository;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Rollback;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest
class JpaPetRepositoryTest {

    @Autowired
    MemberRepository memberRepository;
    @Autowired
    PetRepository petRepository;

    @Test
    @Transactional
    @Rollback(value = false)
    public void 펫등록() {
        //given
        Member member = new Member("memberA", "주인1", "암호", "jin@naver.com", LocalDate.now());
        memberRepository.save(member);
        Pet pet = new Pet("닥스훈트", member, LocalDate.now());

        //when
        Long savedId = petRepository.save(pet);
        Pet findPet = petRepository.findById(savedId);

        //then
        assertThat(findPet.getId()).isEqualTo(pet.getId());
        assertThat(findPet.getName()).isEqualTo(pet.getName());
    }

    @Test
    @Transactional
    @Rollback(value = false)
    public void 펫모아보기() {
        //given
        Member member1 = new Member("memberA", "주인1", "암호1", "jin@naver.com", LocalDate.now());
        memberRepository.save(member1);
        Pet pet1 = new Pet("닥스훈트1", member1, LocalDate.now());
        Pet pet2 = new Pet("닥스훈트2", member1, LocalDate.now());
        petRepository.save(pet1);
        petRepository.save(pet2);

        Member member2 = new Member("memberB", "주인2", "암호2", "jin@naver.com", LocalDate.now());
        memberRepository.save(member2);
        Pet pet3 = new Pet("닥스훈트3", member2, LocalDate.now());
        petRepository.save(pet3);

        //when
        List<Pet> member1List = petRepository.findByMember(member1);
        List<Pet> member2List = petRepository.findByMember(member2);
        List<Pet> pets = petRepository.findAll();

        //then
        assertThat(member1List.size()).isEqualTo(2);
        assertThat(member1.getPets().size()).isEqualTo(2);
        assertThat(member2List.size()).isEqualTo(1);
        assertThat(member2.getPets().size()).isEqualTo(1);
        assertThat(pets.size()).isEqualTo(3);
    }

    @Test
    @Transactional
    void check() {
        petRepository.findAll()
                .stream()
                .filter(pet -> pet.getId() == 0)
                .findAny()
                .ifPresent(pet ->
                        System.out.println(pet.getId())
                );
    }
}