package com.family.petmemory.repository.pet;

import com.family.petmemory.entity.dto.PetDetailForm;
import com.family.petmemory.entity.dto.WeightForm;
import com.family.petmemory.entity.member.Member;
import com.family.petmemory.entity.pet.Pet;
import com.family.petmemory.entity.pet.Weight;
import com.family.petmemory.repository.member.MemberRepository;
import com.family.petmemory.service.PetService;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;

import static org.assertj.core.api.Assertions.*;

@SpringBootTest
class DataJpaWeightRepositoryTest {

    @Autowired
    DataJpaWeightRepository weightRepository;

    @Autowired
    PetService petService;

    @Autowired
    DataJpaPetRepository petRepository;

    @Autowired
    MemberRepository memberRepository;

    @Test
    @Transactional
    void saveTest() {
        //given
        Member member1 = new Member("memberA", "주인1", "암호1", "jin@naver.com", LocalDate.now());
        memberRepository.save(member1);
        Pet pet = new Pet("닥스훈트1", member1, LocalDate.now());
        petRepository.save(pet);

        //when
        weightRepository.save(new Weight(BigDecimal.valueOf(8.85), LocalDate.of(2022, 12, 31), pet));
        weightRepository.save(new Weight(BigDecimal.valueOf(8.65), LocalDate.now(), pet));
        PetDetailForm petDetail = petService.findPetDetail(pet.getId());

        //then
        System.out.println("petDetail.getPath() = " + petDetail.getPath());
        /*for (WeightForm weight : petDetail.getWeights()) {
            System.out.println("weight.getDate() = " + weight.getDate());
            System.out.println("weight.getWeight() = " + weight.getWeight());
        }*/
    }
}