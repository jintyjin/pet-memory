package com.family.petmemory.service;

import com.family.petmemory.entity.dto.MemoryDto;
import com.family.petmemory.entity.dto.MemoryForm;
import com.family.petmemory.entity.dto.MemorySearchCondition;
import com.family.petmemory.entity.dto.MemoryShowForm;
import com.family.petmemory.entity.memory.MemoryStatus;
import com.family.petmemory.entity.memory.MemoryType;
import com.family.petmemory.entity.pet.Pet;
import com.family.petmemory.entity.memory.Memory;
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
    public String join(MemoryForm memoryForm) {
        String profile = null;
        Optional<Pet> optionalPet = petRepository.findById(memoryForm.getPetId());
        if (optionalPet.isPresent()) {
            Pet findPet = optionalPet.get();
            List<MemoryDto> files = memoryForm.getFiles()
                    .stream()
                    .map(file -> new MemoryDto(findPet, file))
                    .collect(Collectors.toList());

            try {
                for (int i = 0; i < files.size(); i++) {
                    profile = saveFiles(files, profile, i);
                }
            } catch (IOException e) {
                deleteFiles(files);

                return null;
            }
        }
        return profile;
    }

    public List<MemoryShowForm> showPetMemories(Long petId, MemoryStatus memoryStatus) {
        return memoryRepository.search(new MemorySearchCondition(petId, null, memoryStatus))
                .stream()
                .map(memory -> new MemoryShowForm(memory.getPet().getId(), memory.getPet().getName(), memory.getId(), memory.getUploadFile().getSaveFileName()))
                .collect(Collectors.toList());
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
        memoryRepository.save(new Memory(memoryDto.getUploadFile(), memoryDto.getPet(), MemoryType.valueOf(memoryDto.getFile().getContentType().split("/")[0].toUpperCase())));
        MultipartFile file = memoryDto.getFile();
        file.transferTo(new File(fileDir + memoryDto.getUploadFile().getSaveFileName()));
        if (i == 0) {
            profile = memoryDto.getUploadFile().getSaveFileName();
        }
        return profile;
    }
}
