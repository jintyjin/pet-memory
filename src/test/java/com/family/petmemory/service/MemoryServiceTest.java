package com.family.petmemory.service;

import com.family.petmemory.entity.dto.MemorySearchCondition;
import com.family.petmemory.entity.member.Member;
import com.family.petmemory.entity.memory.Memory;
import com.family.petmemory.entity.memory.MemoryStatus;
import com.family.petmemory.entity.memory.MemoryType;
import com.family.petmemory.entity.memory.UploadFile;
import com.family.petmemory.entity.pet.Pet;
import com.family.petmemory.repository.member.MemberRepository;
import com.family.petmemory.repository.memory.DataJpaMemoryRepository;
import com.family.petmemory.repository.memory.MemoryRepository;
import com.family.petmemory.repository.pet.DataJpaPetRepository;
import com.family.petmemory.repository.pet.PetRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.mock.web.MockMultipartFile;
import org.springframework.test.annotation.Rollback;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@SpringBootTest
class MemoryServiceTest {

    @Value("${file.dir}")
    private String fileDir;

    @Autowired
    MemberRepository memberRepository;

    @Autowired
    DataJpaPetRepository petRepository;

    @Autowired
    DataJpaMemoryRepository memoryRepository;

    @Test
    @Transactional()
    @Rollback(value = false)
    void 업로드_가능_여부() throws IOException {
        //given
        Member memberA = new Member("memberA", "주인1", "암호1", "jin@naver.com", LocalDate.now());
        memberRepository.save(memberA);
        Pet petA = new Pet("petA", memberA, LocalDate.now());
        petRepository.save(petA);

        //when
        List<MultipartFile> list = new ArrayList<>();
        MultipartFile file1 = new MockMultipartFile(UUID.randomUUID().toString(), "1234.jpg", "image/jpg", new FileInputStream(fileDir + "/KakaoTalk_20200629_004002411.jpg"));
        MultipartFile file2 = new MockMultipartFile(UUID.randomUUID().toString(), "1234.jpg", "image/jpg", new FileInputStream(fileDir + "/KakaoTalk_20200629_004005067.jpg"));
        list.add(file1);
        list.add(file2);

        //then
        for (MultipartFile file : list) {
            String saveFileName = file.getName() + "." + file.getContentType().split("/")[1];
            String uploadFileName = file.getOriginalFilename();
            memoryRepository.save(new Memory(new UploadFile(uploadFileName, saveFileName), petA, MemoryType.valueOf(file.getContentType().split("/")[0].toUpperCase())));
            File saveFile = new File(fileDir + saveFileName);
            file.transferTo(saveFile);
//            saveFile.delete();
        }
    }

    @Test
    @Transactional
    void 업로드_파일_타입_확인() throws IOException {
        //given
        Member memberA = new Member("memberA", "주인1", "암호1", "jin@naver.com", LocalDate.now());
        memberRepository.save(memberA);
        Pet petA = new Pet("petA", memberA, LocalDate.now());
        petRepository.save(petA);

        //when
        List<MultipartFile> list = new ArrayList<>();
        MultipartFile file1 = new MockMultipartFile(UUID.randomUUID().toString(), "1234.jpg", "image/jpg", new FileInputStream(fileDir + "/KakaoTalk_20200629_004002411.jpg"));
        MultipartFile file2 = new MockMultipartFile(UUID.randomUUID().toString(), "1234.jpg", "image/jpg", new FileInputStream(fileDir + "/KakaoTalk_20200629_004005067.jpg"));
        MultipartFile file3 = new MockMultipartFile(UUID.randomUUID().toString(), "1234.mp4", "video/mp4", new FileInputStream(fileDir + "/kakaotalk_1544571817654.mp4"));
        MultipartFile file4 = new MockMultipartFile(UUID.randomUUID().toString(), "1234.mp4", "video/mp4", new FileInputStream(fileDir + "/kakaotalk_1544571819513.mp4"));
        list.add(file1);
        list.add(file2);
        list.add(file3);
        list.add(file4);

        //then
        for (MultipartFile file : list) {
            System.out.println("file.getContentType() = " + file.getContentType().split("/")[0].toUpperCase());
        }
    }

    @Test
    @Transactional
    void searchTest() throws IOException {
        //given
        Member memberA = new Member("memberA", "주인1", "암호1", "jin@naver.com", LocalDate.now());
        memberRepository.save(memberA);
        Pet petA = new Pet("petA", memberA, LocalDate.now());
        petRepository.save(petA);

        //when
        List<MultipartFile> list = new ArrayList<>();
        MultipartFile file1 = new MockMultipartFile(UUID.randomUUID().toString(), "1234.jpg", "image/jpg", new FileInputStream(fileDir + "/KakaoTalk_20200629_004002411.jpg"));
        MultipartFile file2 = new MockMultipartFile(UUID.randomUUID().toString(), "1234.jpg", "image/jpg", new FileInputStream(fileDir + "/KakaoTalk_20200629_004005067.jpg"));
        MultipartFile file3 = new MockMultipartFile(UUID.randomUUID().toString(), "1234.mp4", "video/mp4", new FileInputStream(fileDir + "/kakaotalk_1544571817654.mp4"));
        MultipartFile file4 = new MockMultipartFile(UUID.randomUUID().toString(), "1234.mp4", "video/mp4", new FileInputStream(fileDir + "/kakaotalk_1544571819513.mp4"));
        list.add(file1);
        list.add(file2);
        list.add(file3);
        list.add(file4);

        for (MultipartFile file : list) {
            memoryRepository.save(new Memory(new UploadFile(file.getOriginalFilename(), file.getName()), petA, MemoryType.IMAGE));
        }

        List<Memory> findMemories = memoryRepository.search(new MemorySearchCondition(petA.getId(), null, MemoryStatus.NORMAL, null));

        //then
        for (Memory findMemory : findMemories) {
            System.out.println("findMemory.getPet().getName() = " + findMemory.getPet().getName());
            System.out.println("findMemory.getUploadFile().getSaveFileName() = " + findMemory.getUploadFile().getSaveFileName());
        }
        System.out.println("findMemories.size() = " + findMemories.size());
    }
}