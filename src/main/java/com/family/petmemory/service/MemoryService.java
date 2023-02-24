package com.family.petmemory.service;

import com.drew.imaging.ImageProcessingException;
import com.family.petmemory.entity.dto.MemoryDto;
import com.family.petmemory.entity.dto.MemoryForm;
import com.family.petmemory.entity.dto.MemorySearchCondition;
import com.family.petmemory.entity.dto.MemoryShowForm;
import com.family.petmemory.entity.memory.*;
import com.family.petmemory.entity.pet.Pet;
import com.family.petmemory.infra.ImageUtil;
import com.family.petmemory.repository.memory.DataJpaMemoryRepository;
import com.family.petmemory.repository.pet.DataJpaPetRepository;
import com.family.petmemory.repository.pet.PetRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class MemoryService {

    private final DataJpaPetRepository petRepository;
    private final DataJpaMemoryRepository memoryRepository;

    @Value("${file.dir}")
    private String fileDir;

    @Transactional
    public Long join(MemoryForm memoryForm) {
        Long profile = null;
        Optional<Pet> optionalPet = petRepository.findById(memoryForm.getPetId());

        if (optionalPet.isPresent()) {
            Pet findPet = optionalPet.get();
            List<MemoryDto> files = getMemoryDtos(memoryForm, findPet);

            try {
                for (int i = 0; i < files.size(); i++) {
                    profile = saveFiles(files, profile, i);
                }
            } catch (Exception e) {
                e.printStackTrace();
                deleteFiles(files);
                return null;
            }
        }
        return profile;
    }

    private List<MemoryDto> getMemoryDtos(MemoryForm memoryForm, Pet findPet) {
        List<MemoryDto> files = memoryForm.getFiles()
                .stream()
                .map(file -> new MemoryDto(findPet, file))
                .collect(Collectors.toList());
        return files;
    }

    public List<MemoryShowForm> showPetMemories(Long petId, MemoryStatus memoryStatus) {
        return memoryRepository.search(new MemorySearchCondition(petId, null, memoryStatus, null))
                .stream()
                .map(memory -> new MemoryShowForm(memory.getPet().getId(), memory.getPet().getName(), memory.getId(), memory.getUploadFile().getSaveFileName(), memory.getMemoryType()))
                .collect(Collectors.toList());
    }

    private void deleteFiles(List<MemoryDto> files) {
        for (int i = 0; i < files.size(); i++) {
            File file = new File(fileDir + files.get(i).getUploadFile().getSaveFileName());
            if (file.exists()) {
                file.delete();
            }
        }
    }

    private Long saveFiles(List<MemoryDto> files, Long profile, int i) throws IOException, ImageProcessingException {
        MemoryDto memoryDto = files.get(i);
        String path = saveImage(memoryDto);
        Memory savedMemory = saveMemory(memoryDto, path);
        if (i == 0) {
            profile = savedMemory.getId();
        }
        return profile;
    }

    private Memory saveMemory(MemoryDto memoryDto, String path) throws ImageProcessingException, IOException {
        File file = new File(path);
        LocalDateTime imageTime = ImageUtil.extractLocalDateTime(file);
        Gps gps = ImageUtil.extractGps(file);
        ImageSize imageSize = ImageUtil.extractImageSize(file);
        return memoryRepository.save(new Memory(memoryDto.getUploadFile(), imageTime, imageSize, gps, memoryDto.getPet(), MemoryType.valueOf(memoryDto.getFile().getContentType().split("/")[0].toUpperCase())));
    }

    private String saveImage(MemoryDto memoryDto) throws IOException {
        MultipartFile file = memoryDto.getFile();
        String path = fileDir + memoryDto.getUploadFile().getSaveFileName();
        file.transferTo(new File(path));
        return path;
    }
}
