package com.family.petmemory.repository.memory;

import com.family.petmemory.entity.memory.*;
import com.family.petmemory.entity.member.Member;
import com.family.petmemory.entity.pet.Pet;
import com.family.petmemory.infra.ImageSize;
import com.family.petmemory.infra.UploadFile;
import com.family.petmemory.repository.member.MemberRepository;
import com.family.petmemory.repository.pet.PetRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Rollback;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

import static org.assertj.core.api.Assertions.*;

@SpringBootTest
class JpaMemoryRepositoryTest {

    @Autowired
    MemberRepository memberRepository;
    @Autowired
    PetRepository petRepository;
    @Autowired
    MemoryRepository imageRepository;

    @Test
    @Transactional
    @Rollback(value = false)
    public void 이미지_등록() {
        //given
        Member memberA = new Member("memberA", "주인1", "암호1", "jin@naver.com", LocalDate.now());
        memberRepository.save(memberA);
        Pet petA = new Pet("petA", memberA, LocalDate.now());
        petRepository.save(petA);

        //when
        Memory imageA = new Memory(new UploadFile("/home/folder/imageA.jpg", "/home/folder/imageA.jpg"), LocalDateTime.now(), new ImageSize(0, 0), new Gps(0.0, 0.0), petA, MemoryType.IMAGE);
        Memory imageB = new Memory(new UploadFile("/home/folder/imageB.jpg", "/home/folder/imageB.jpg"), LocalDateTime.now(), new ImageSize(0, 0), new Gps(0.0, 0.0),petA, MemoryType.IMAGE);
        Memory imageC = new Memory(new UploadFile("/home/folder/imageC.jpg", "/home/folder/imageC.jpg"), LocalDateTime.now(), new ImageSize(0, 0), new Gps(0.0, 0.0),petA, MemoryType.IMAGE);
        Memory imageD = new Memory(new UploadFile("/home/folder/imageD.jpg", "/home/folder/imageD.jpg"), LocalDateTime.now(), new ImageSize(0, 0), new Gps(0.0, 0.0),petA, MemoryType.IMAGE);
        Long savedId = imageRepository.save(imageA);
        imageRepository.save(imageB);
        imageRepository.save(imageC);
        imageRepository.save(imageD);
        Memory findImage = imageRepository.findById(savedId);

        //then
        assertThat(savedId).isEqualTo(findImage.getId());
    }

    @Test
    @Transactional
    @Rollback(value = false)
    public void 이미지_검색() {
        //given
        Member memberA = new Member("memberA", "주인1", "암호1", "jin@naver.com", LocalDate.now());
        memberRepository.save(memberA);
        Pet petA = new Pet("petA", memberA, LocalDate.now());
        petRepository.save(petA);

        Member memberB = new Member("memberB", "주인2", "암호2", "jin@naver.com", LocalDate.now());
        memberRepository.save(memberB);
        Pet petB = new Pet("petB", memberB, LocalDate.now());
        petRepository.save(petB);

        //when
        Memory imageA = new Memory(new UploadFile("/home/folder/imageA.jpg", "/home/folder/imageA.jpg"), LocalDateTime.now(), new ImageSize(0, 0), new Gps(0.0, 0.0),petA, MemoryType.IMAGE);
        Memory imageB = new Memory(new UploadFile("/home/folder/imageB.jpg", "/home/folder/imageB.jpg"), LocalDateTime.now(), new ImageSize(0, 0), new Gps(0.0, 0.0),petA, MemoryType.IMAGE);
        Memory imageC = new Memory(new UploadFile("/home/folder/imageC.jpg", "/home/folder/imageC.jpg"), LocalDateTime.now(), new ImageSize(0, 0), new Gps(0.0, 0.0),petB, MemoryType.IMAGE);
        Memory imageD = new Memory(new UploadFile("/home/folder/imageD.jpg", "/home/folder/imageD.jpg"), LocalDateTime.now(), new ImageSize(0, 0), new Gps(0.0, 0.0),petB, MemoryType.IMAGE);
        imageRepository.save(imageA);
        imageRepository.save(imageB);
        imageRepository.save(imageC);
        imageRepository.save(imageD);

        List<Memory> petAImages = imageRepository.findByPet(petA);
        List<Memory> petBImages = imageRepository.findByPet(petB);
        List<Memory> allImages = imageRepository.findAll();

        //then
        assertThat(petAImages.size()).isEqualTo(2);
        assertThat(petBImages.size()).isEqualTo(2);
        assertThat(allImages.size()).isEqualTo(4);
    }
}