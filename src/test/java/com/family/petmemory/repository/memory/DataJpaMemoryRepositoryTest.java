package com.family.petmemory.repository.memory;

import com.family.petmemory.entity.dto.MemorySearchCondition;
import com.family.petmemory.entity.member.Member;
import com.family.petmemory.entity.memory.Memory;
import com.family.petmemory.entity.memory.UploadFile;
import com.family.petmemory.entity.pet.Pet;
import com.family.petmemory.repository.member.JpaMemberRepository;
import com.family.petmemory.repository.member.MemberRepository;
import com.family.petmemory.repository.pet.PetRepository;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.util.List;

import static org.assertj.core.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@Transactional
class DataJpaMemoryRepositoryTest {

    @Autowired
    MemberRepository memberRepository;

    @Autowired
    PetRepository petRepository;

    @Autowired
    DataJpaMemoryRepository memoryRepository;

    @Test
    void basicTest() {
        //given
        Member memberA = new Member("memberA", "주인1", "암호1", "jin@naver.com", LocalDate.now());
        memberRepository.save(memberA);
        Pet petA = new Pet("petA", memberA, LocalDate.now());
        petRepository.save(petA);

        //when
        Memory imageA = new Memory(new UploadFile("/home/folder/imageA.jpg", "/home/folder/imageA.jpg"), petA);
        Memory imageB = new Memory(new UploadFile("/home/folder/imageB.jpg", "/home/folder/imageB.jpg"), petA);
        Memory imageC = new Memory(new UploadFile("/home/folder/imageC.jpg", "/home/folder/imageC.jpg"), petA);
        Memory imageD = new Memory(new UploadFile("/home/folder/imageD.jpg", "/home/folder/imageD.jpg"), petA);
        memoryRepository.save(imageA);
        memoryRepository.save(imageB);
        memoryRepository.save(imageC);
        memoryRepository.save(imageD);
        List<Memory> memories = memoryRepository.search(new MemorySearchCondition(petA.getId(), null));
        List<Memory> memory = memoryRepository.search(new MemorySearchCondition(null, imageA.getId()));

        //then
        assertThat(petA.getMemories().size()).isEqualTo(4);
        assertThat(memories.size()).isEqualTo(4);
        assertThat(memory.size()).isEqualTo(1);
    }
}