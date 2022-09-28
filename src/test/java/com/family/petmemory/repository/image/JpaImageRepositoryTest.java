package com.family.petmemory.repository.image;

import com.family.petmemory.entity.image.Image;
import com.family.petmemory.entity.member.Member;
import com.family.petmemory.entity.pet.Pet;
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
class JpaImageRepositoryTest {

    @Autowired
    MemberRepository memberRepository;
    @Autowired
    PetRepository petRepository;
    @Autowired
    ImageRepository imageRepository;

    @Test
    @Transactional
    @Rollback(value = false)
    public void 이미지_등록() {
        //given
        Member memberA = new Member("memberA", "주인1", "암호1", "jin@naver.com", LocalDate.now());
        memberRepository.save(memberA);
        Pet petA = new Pet("petA", memberA, LocalDateTime.now());
        petRepository.save(petA);

        //when
        Image imageA = new Image("/home/folder/imageA.jpg", petA);
        Image imageB = new Image("/home/folder/imageB.jpg", petA);
        Image imageC = new Image("/home/folder/imageC.jpg", petA);
        Image imageD = new Image("/home/folder/imageD.jpg", petA);
        Long savedId = imageRepository.save(imageA);
        imageRepository.save(imageB);
        imageRepository.save(imageC);
        imageRepository.save(imageD);
        Image findImage = imageRepository.findById(savedId);

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
        Pet petA = new Pet("petA", memberA, LocalDateTime.now());
        petRepository.save(petA);

        Member memberB = new Member("memberB", "주인2", "암호2", "jin@naver.com", LocalDate.now());
        memberRepository.save(memberB);
        Pet petB = new Pet("petB", memberB, LocalDateTime.now());
        petRepository.save(petB);

        //when
        Image imageA = new Image("/home/folder/imageA.jpg", petA);
        Image imageB = new Image("/home/folder/imageB.jpg", petA);
        Image imageC = new Image("/home/folder/imageC.jpg", petB);
        Image imageD = new Image("/home/folder/imageD.jpg", petB);
        imageRepository.save(imageA);
        imageRepository.save(imageB);
        imageRepository.save(imageC);
        imageRepository.save(imageD);

        List<Image> petAImages = imageRepository.findByPet(petA);
        List<Image> petBImages = imageRepository.findByPet(petB);
        List<Image> allImages = imageRepository.findAll();

        //then
        assertThat(petAImages.size()).isEqualTo(2);
        assertThat(petBImages.size()).isEqualTo(2);
        assertThat(allImages.size()).isEqualTo(4);
    }
}