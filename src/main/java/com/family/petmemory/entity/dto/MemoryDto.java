package com.family.petmemory.entity.dto;

import com.family.petmemory.infra.UploadFile;
import com.family.petmemory.entity.pet.Pet;
import lombok.Data;
import org.springframework.web.multipart.MultipartFile;

import java.util.UUID;

@Data
public class MemoryDto {

    private Pet pet;

    private UploadFile uploadFile;

    private MultipartFile file;

    public MemoryDto(Pet pet, MultipartFile file) {
        this.pet = pet;
        this.file = file;
        this.uploadFile = new UploadFile(file.getOriginalFilename(), UUID.randomUUID().toString().replaceAll("-", "_") + "." + file.getContentType().split("/")[1]);
    }
}
