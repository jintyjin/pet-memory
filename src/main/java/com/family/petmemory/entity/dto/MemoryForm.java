package com.family.petmemory.entity.dto;

import lombok.Data;
import org.springframework.web.multipart.MultipartFile;

import java.util.ArrayList;
import java.util.List;

@Data
public class MemoryForm {

    private Long petId;

    private List<MultipartFile> files = new ArrayList<>();

    public MemoryForm(Long petId) {
        this.petId = petId;
    }

    public void addFile(MultipartFile file) {
        this.files.add(file);
    }
}
