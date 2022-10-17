package com.family.petmemory.service;

import com.family.petmemory.entity.dto.MemoryDto;
import com.family.petmemory.entity.dto.MemoryForm;
import com.family.petmemory.entity.pet.Pet;
import com.family.petmemory.entity.memory.Memory;
import com.family.petmemory.entity.memory.UploadFile;
import com.family.petmemory.repository.pet.PetRepository;
import com.family.petmemory.repository.memory.MemoryRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class MemoryService {

    private final PetRepository petRepository;
    private final MemoryRepository memoryRepository;

    @Value("${file.dir}")
    private String fileDir;

    @Transactional
    public String join(MemoryForm memoryForm) {
        Pet findPet = petRepository.findById(memoryForm.getPetId());
        List<MemoryDto> files = memoryForm.getFiles()
                .stream()
                .map(file -> new MemoryDto(findPet, file))
                .collect(Collectors.toList());
        String profile = null;

        try {
            for (int i = 0; i < files.size(); i++) {
                profile = saveFiles(files, profile, i);
            }
        } catch (IOException e) {
            deleteFiles(files);

            return null;
        }

        return profile;
    }

    private void deleteFiles(List<MemoryDto> files) {
        for (int i = 0; i < files.size(); i++) {
            MemoryDto memoryDto = files.get(i);
            File file = new File(fileDir + memoryDto.getUploadFile().getSaveFileName());
            if (file.exists()) {
                file.delete();
            }
        }
    }

    private String saveFiles(List<MemoryDto> files, String profile, int i) throws IOException {
        MemoryDto memoryDto = files.get(i);
        memoryRepository.save(new Memory(memoryDto.getUploadFile(), memoryDto.getPet()));
        MultipartFile file = memoryDto.getFile();
        file.transferTo(new File(fileDir + memoryDto.getUploadFile().getSaveFileName()));
        if (i == 0) {
            profile = memoryDto.getUploadFile().getSaveFileName();
        }
        return profile;
    }
}
