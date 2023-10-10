package com.family.petmemory.service;

import com.drew.imaging.ImageMetadataReader;
import com.drew.imaging.ImageProcessingException;
import com.drew.metadata.Metadata;
import com.drew.metadata.exif.ExifSubIFDDirectory;
import com.drew.metadata.exif.GpsDirectory;
import com.family.petmemory.entity.dto.MemorySearchCondition;
import com.family.petmemory.entity.member.Member;
import com.family.petmemory.entity.memory.*;
import com.family.petmemory.entity.pet.Pet;
import com.family.petmemory.infra.ImageSize;
import com.family.petmemory.infra.UploadFile;
import com.family.petmemory.repository.member.MemberRepository;
import com.family.petmemory.repository.memory.DataJpaMemoryRepository;
import com.family.petmemory.repository.pet.DataJpaPetRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.mock.web.MockMultipartFile;
import org.springframework.test.annotation.Rollback;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.UUID;

import static org.assertj.core.api.Assertions.*;

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
            memoryRepository.save(new Memory(new UploadFile(uploadFileName, saveFileName), LocalDateTime.now(), new ImageSize(0, 0), new Gps(0.0, 0.0), petA, MemoryType.valueOf(file.getContentType().split("/")[0].toUpperCase())));
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
            memoryRepository.save(new Memory(new UploadFile(file.getOriginalFilename(), file.getName()), LocalDateTime.now(), new ImageSize(0, 0), new Gps(0.0, 0.0), petA, MemoryType.IMAGE));
        }

        List<Memory> findMemories = memoryRepository.search(new MemorySearchCondition(petA.getId(), null, MemoryStatus.NORMAL, null));

        //then
        for (Memory findMemory : findMemories) {
            System.out.println("findMemory.getPet().getName() = " + findMemory.getPet().getName());
            System.out.println("findMemory.getUploadFile().getSaveFileName() = " + findMemory.getUploadFile().getSaveFileName());
        }
        System.out.println("findMemories.size() = " + findMemories.size());
    }

    @Test
    void 시간_메타데이터_확인() throws IOException, ImageProcessingException {
        //given
//        File file = new File(fileDir + "/IMG_3543.HEIC");
        File file = new File(fileDir + "/e02d0b6b_0821_4aab_b58e_69dd027ca147.jpeg");

        //when
        Metadata metadata = ImageMetadataReader.readMetadata(file);

        ExifSubIFDDirectory directory = metadata.getFirstDirectoryOfType(ExifSubIFDDirectory.class);

        Date date = directory.getDate(ExifSubIFDDirectory.TAG_DATETIME_ORIGINAL);

        //then
        try {
            LocalDateTime localDateTime = LocalDateTime.ofInstant(date.toInstant(), ZoneId.systemDefault());
            System.out.println("data = " + localDateTime);
        } catch(NullPointerException e) {
            System.out.println("Exception = " + directory);
            System.out.println("Exception = " + date);
        }
    }

    @Test
    void GPS_데이터_확인() throws IOException, ImageProcessingException {
        //given
//        File file = new File(fileDir + "/IMG_3543.HEIC");
        File file = new File(fileDir + "/e02d0b6b_0821_4aab_b58e_69dd027ca147.jpeg");

        //when
        Metadata metadata = ImageMetadataReader.readMetadata(file);
        GpsDirectory directory = metadata.getFirstDirectoryOfType(GpsDirectory.class);

        //then
        try {
            System.out.println(directory.getGeoLocation().getLongitude());
            System.out.println(directory.getGeoLocation().getLatitude());
        } catch(NullPointerException e) {
            System.out.println("Exception = " + directory);
        }
    }

    @Test
    @Transactional
    void 메모리_데이터_저장() {
        //given
        Member memberA = new Member("memberA", "주인1", "암호1", "jin@naver.com", LocalDate.now());
        memberRepository.save(memberA);
        Pet petA = new Pet("petA", memberA, LocalDate.now());
        petRepository.save(petA);

        //when
        LocalDateTime now = LocalDateTime.now();
        Memory memory = new Memory(new UploadFile(fileDir + "/IMG_3543.HEIC", UUID.randomUUID().toString().replaceAll("-", "_") + ".jpg"), now, new ImageSize(0, 0), new Gps(0.0, 0.0), petA, MemoryType.IMAGE);
        Memory savedMemory = memoryRepository.save(memory);

        //then
        assertThat(now).isEqualTo(savedMemory.getManageTime().getImageTime());
        assertThat(0.0).isEqualTo(savedMemory.getGps().getLatitude());
        assertThat(0.0).isEqualTo(savedMemory.getGps().getLongitude());
    }

    @Test
    void 이미지_크기_데이터_추출() throws IOException {
        //given
        File file = new File(fileDir + "/pet_thumbnail.png");
//        File file = new File(fileDir + "/e5335e5c_49f3_491b_b1dd_e16d95725ebc.jpeg");
//        File file = new File(fileDir + "/e02d0b6b_0821_4aab_b58e_69dd027ca147.jpeg");

        //when
        BufferedImage bi = ImageIO.read(file);

        //then
        System.out.println("bi.getWidth() = " + bi.getWidth());
        System.out.println("bi.getHeight() = " + bi.getHeight());
    }
}