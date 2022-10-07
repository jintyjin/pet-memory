package com.family.petmemory.service;

import com.family.petmemory.entity.dto.MemoryForm;
import com.family.petmemory.entity.dto.PetForm;
import com.family.petmemory.entity.member.Member;
import com.family.petmemory.entity.pet.Pet;
import com.family.petmemory.repository.member.MemberRepository;
import com.family.petmemory.repository.pet.PetRepository;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.mock.web.MockMultipartFile;
import org.springframework.test.annotation.Rollback;
import org.springframework.transaction.annotation.Transactional;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.time.LocalDate;
import java.util.List;
import java.util.stream.Collectors;

import static org.assertj.core.api.Assertions.*;
import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class PetServiceTest {

    @Autowired
    MemberRepository memberRepository;

    @Autowired
    PetService petService;

    @Autowired
    PetRepository petRepository;

    @Value("${file.dir}")
    String fileDir;

    @Test
    @Transactional
    @Rollback(value = false)
    void joinTest() throws IOException {
        //given
        MockMultipartFile file = new MockMultipartFile("1.jpg", "1234.jpg", "image/jpg", new FileInputStream(fileDir + "/KakaoTalk_20200629_004002411.jpg"));
        Member member = new Member("memberA", "주인1", "암호", "jin@naver.com", LocalDate.now());
        Long savedId = memberRepository.save(member);
        Member findMember = memberRepository.findById(savedId);

        //when
        petService.join(new PetForm(savedId, "petA", LocalDate.now(), file));
        petService.join(new PetForm(savedId, "petB", LocalDate.now(), file));
        petService.join(new PetForm(savedId, "petC", LocalDate.now(), file));

        //then
        assertThat(member.getId()).isEqualTo(findMember.getId());
        List<Pet> pets = member.getPets();
        for (Pet pet : pets) {
            System.out.println("pet.getName() = " + pet.getName());            
        }
    }

    @Test
    @Transactional
    void 메모리테스트() throws IOException {
        //given
        MockMultipartFile file = new MockMultipartFile("1.jpg", "1234.jpg", "image/jpg", new FileInputStream(fileDir + "/KakaoTalk_20200629_004002411.jpg"));
        Member member = new Member("testMember123", "홍길동", "testMember!23", "jin@naver.com", LocalDate.now());
        Long savedId = memberRepository.save(member);
        Long savedPetId1 = petService.join(new PetForm(savedId, "이복댕", LocalDate.now(), file));
        Long savedPetId2 = petService.join(new PetForm(savedId, "이북댕", LocalDate.now(), file));
        Pet findPet1 = petRepository.findById(savedPetId1);
        Pet findPet2 = petRepository.findById(savedPetId2);
        findPet1.changeThumbnail("/KakaoTalk_20200629_004002411.jpg");
        findPet2.changeThumbnail("/KakaoTalk_20200629_004005067.jpg");

        //when
        List<MemoryForm> pets = petService.findMyPets(member)
                .stream()
                .map(pet -> new MemoryForm(pet.getName(), pet.getThumbnail()))
                .collect(Collectors.toList());

        for (MemoryForm pet : pets) {
            System.out.println(pet.getName() + " = " + pet.getThumbnail());
        }

        //then
        assertThat(pets.size()).isEqualTo(2);
    }
}