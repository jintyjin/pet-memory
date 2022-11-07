package com.family.petmemory.repository.pet;

import com.family.petmemory.entity.dto.PetProfileForm;
import com.family.petmemory.entity.member.Member;
import com.family.petmemory.entity.memory.Memory;
import com.family.petmemory.entity.memory.MemoryType;
import com.family.petmemory.entity.memory.UploadFile;
import com.family.petmemory.entity.pet.Pet;
import com.family.petmemory.repository.member.MemberRepository;
import com.family.petmemory.repository.memory.DataJpaMemoryRepository;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Rollback;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.util.List;

import static org.assertj.core.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class CustomPetRepositoryImplTest {

    @Autowired
    MemberRepository memberRepository;

    @Autowired
    DataJpaPetRepository petRepository;

    @Autowired
    DataJpaMemoryRepository memoryRepository;

    @Test
    @Transactional
    @Rollback(value = false)
    void BasicTest() {
        //given
        Member memberA = new Member("memberA", "주인1", "암호1", "jin@naver.com", LocalDate.now());
        memberRepository.save(memberA);
        Pet petA = new Pet("petA", memberA, LocalDate.now());
        Pet petB = new Pet("petB", memberA, LocalDate.now());
        petRepository.save(petA);
        petRepository.save(petB);
        Memory imageA = new Memory(new UploadFile("/home/folder/imageA.jpg", "/home/folder/imageA.jpg"), petA, MemoryType.IMAGE);
        Memory imageB = new Memory(new UploadFile("/home/folder/imageB.jpg", "/home/folder/imageB.jpg"), petA, MemoryType.IMAGE);
        Memory imageC = new Memory(new UploadFile("/home/folder/imageC.jpg", "/home/folder/imageC.jpg"), petB, MemoryType.IMAGE);
        Memory imageD = new Memory(new UploadFile("/home/folder/imageD.jpg", "/home/folder/imageD.jpg"), petB, MemoryType.IMAGE);
        memoryRepository.save(imageA);
        memoryRepository.save(imageB);
        memoryRepository.save(imageC);
        memoryRepository.save(imageD);
        petA.changeProfile(imageA.getId());
        petB.changeProfile(imageC.getId());

        //when
        List<PetProfileForm> petProfiles = petRepository.findPetProfiles(memberA);

        //then
        assertThat(petProfiles.size()).isEqualTo(2);
        for (PetProfileForm petProfile : petProfiles) {
            System.out.println("petProfile.getId() = " + petProfile.getId());
            System.out.println("petProfile.getName() = " + petProfile.getName());
            System.out.println("petProfile.getPath() = " + petProfile.getPath());
            System.out.println("petProfile.getType() = " + petProfile.getType());
        }
    }

}