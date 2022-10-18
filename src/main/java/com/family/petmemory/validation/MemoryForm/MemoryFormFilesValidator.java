package com.family.petmemory.validation.MemoryForm;

import com.family.petmemory.entity.dto.MemoryForm;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.web.multipart.MultipartFile;

@Component
public class MemoryFormFilesValidator extends MemoryFormValidator {

    @Override
    public void validate(Object target, Errors errors) {
        MemoryForm memoryForm = (MemoryForm) target;

        for (MultipartFile file : memoryForm.getFiles()) {
            if (file.isEmpty()) {
                errors.rejectValue("files", "filesSizeZero");
            }
            return;
        }
    }
}
