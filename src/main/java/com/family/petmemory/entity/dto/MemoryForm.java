package com.family.petmemory.entity.dto;

import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.web.multipart.MultipartFile;

import javax.validation.constraints.NotNull;
import java.util.ArrayList;
import java.util.List;

@Data
@NoArgsConstructor
public class MemoryForm {

    @NotNull
    private Long petId;

    private List<MultipartFile> files = new ArrayList<>();

    public MemoryForm(Long petId) {
        this.petId = petId;
    }

    public void addFile(MultipartFile file) {
        this.files.add(file);
    }
}
