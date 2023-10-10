package com.family.petmemory.repository.pet;

import com.family.petmemory.entity.dto.PetDetailForm;
import com.family.petmemory.entity.dto.PetProfileForm;
import com.family.petmemory.entity.member.Member;
import com.family.petmemory.entity.memory.*;
import com.family.petmemory.entity.pet.Pet;
import com.family.petmemory.entity.pet.Weight;
import com.family.petmemory.infra.ImageSize;
import com.family.petmemory.infra.UploadFile;
import com.family.petmemory.repository.member.MemberRepository;
import com.family.petmemory.repository.memory.DataJpaMemoryRepository;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Rollback;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.Month;
import java.util.List;

import static org.assertj.core.api.Assertions.*;

@SpringBootTest
class CustomPetRepositoryImplTest {

    @Autowired
    MemberRepository memberRepository;

    @Autowired
    DataJpaPetRepository petRepository;

    @Autowired
    DataJpaMemoryRepository memoryRepository;

    @Autowired
    DataJpaWeightRepository weightRepository;

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
        Memory imageA = new Memory(new UploadFile("/home/folder/imageA.jpg", "/home/folder/imageA.jpg"), LocalDateTime.now(), new ImageSize(0, 0), new Gps(0.0, 0.0),petA, MemoryType.IMAGE);
        Memory imageB = new Memory(new UploadFile("/home/folder/imageB.jpg", "/home/folder/imageB.jpg"), LocalDateTime.now(), new ImageSize(0, 0), new Gps(0.0, 0.0),petA, MemoryType.IMAGE);
        Memory imageC = new Memory(new UploadFile("/home/folder/imageC.jpg", "/home/folder/imageC.jpg"), LocalDateTime.now(), new ImageSize(0, 0), new Gps(0.0, 0.0),petB, MemoryType.IMAGE);
        Memory imageD = new Memory(new UploadFile("/home/folder/imageD.jpg", "/home/folder/imageD.jpg"), LocalDateTime.now(), new ImageSize(0, 0), new Gps(0.0, 0.0),petB, MemoryType.IMAGE);
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

    @Test
    @Transactional
    @Rollback(value = false)
    void profileTest() {
        //given
        Member memberA = new Member("memberA", "주인1", "암호1", "jin@naver.com", LocalDate.now());
        memberRepository.save(memberA);
        Pet petA = new Pet("petA", memberA, LocalDate.now());
        petRepository.save(petA);
        Memory imageA = new Memory(new UploadFile("/home/folder/imageA.jpg", "/home/folder/imageA.jpg"), LocalDateTime.now(), new ImageSize(0, 0), new Gps(0.0, 0.0),petA, MemoryType.IMAGE);
        Memory imageB = new Memory(new UploadFile("/home/folder/imageB.jpg", "/home/folder/imageB.jpg"), LocalDateTime.now(), new ImageSize(0, 0), new Gps(0.0, 0.0),petA, MemoryType.IMAGE);
        Memory imageC = new Memory(new UploadFile("/home/folder/imageC.jpg", "/home/folder/imageC.jpg"), LocalDateTime.now(), new ImageSize(0, 0), new Gps(0.0, 0.0),petA, MemoryType.IMAGE);
        Memory imageD = new Memory(new UploadFile("/home/folder/imageD.jpg", "/home/folder/imageD.jpg"), LocalDateTime.now(), new ImageSize(0, 0), new Gps(0.0, 0.0),petA, MemoryType.IMAGE);
        memoryRepository.save(imageA);
        memoryRepository.save(imageB);
        memoryRepository.save(imageC);
        memoryRepository.save(imageD);
        petA.changeProfile(imageA.getId());

        //when
        Weight weight1 = new Weight(BigDecimal.valueOf(8.8), LocalDate.now(), petA);
        Weight weight2 = new Weight(BigDecimal.valueOf(8.71), LocalDate.of(2020, Month.DECEMBER, 18), petA);
        Weight weight3 = new Weight(BigDecimal.valueOf(8.6), LocalDate.of(2020, Month.DECEMBER, 19), petA);
        weightRepository.save(weight1);
        weightRepository.save(weight2);
        weightRepository.save(weight3);
        PetDetailForm petDetail = petRepository.findPetDetail(petA.getId());

        //then
        Assertions.assertThat(petA.getWeights().size()).isEqualTo(3);
        petA.getWeights()
                .stream()
                .forEach(weight -> System.out.println(weight.getDate() + " / " + weight.getWeight()));

//        petDetail.getWeights()
//                .stream()
//                .forEach(weightForm -> System.out.println(weightForm.getDate() + " / " + weightForm.getWeight()));
    }
}