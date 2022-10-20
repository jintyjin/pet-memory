package com.family.petmemory.service;

import com.family.petmemory.entity.dto.PetProfileForm;
import com.family.petmemory.entity.dto.PetForm;
import com.family.petmemory.entity.member.Member;
import com.family.petmemory.entity.pet.Pet;
import com.family.petmemory.repository.member.MemberRepository;
import com.family.petmemory.repository.pet.PetRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.mock.web.MockMultipartFile;
import org.springframework.test.annotation.Rollback;
import org.springframework.transaction.annotation.Transactional;

import java.io.FileInputStream;
import java.io.IOException;
import java.time.LocalDate;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

import static org.assertj.core.api.Assertions.assertThat;

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
        findPet1.changeProfile(UUID.randomUUID().toString());
        findPet2.changeProfile(UUID.randomUUID().toString());

        //when
        List<PetProfileForm> pets = petService.findMyPets(member);

        for (PetProfileForm pet : pets) {
            System.out.println(pet.getName() + " = " + pet.getPath());
        }

        //then
        assertThat(pets.size()).isEqualTo(2);
    }

    @Test
    @Transactional
    @Rollback(value = false)
    void 펫목록() {
        //given
        Member member = new Member("testMember123", "홍길동", "testMember!23", "jin@naver.com", LocalDate.now());
        memberRepository.save(member);

        //when
        List<PetProfileForm> petProfileForms = petService.findMyPets(member);

        //then
        assertThat(petProfileForms.size()).isEqualTo(0);
    }
}