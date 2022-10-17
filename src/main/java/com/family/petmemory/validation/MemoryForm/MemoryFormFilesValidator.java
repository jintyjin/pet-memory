package com.family.petmemory.validation.MemoryForm;

import com.family.petmemory.entity.dto.MemoryForm;
import org.springframework.validation.Errors;

public class MemoryFormFilesValidator extends MemoryFormValidator {

    @Override
    public void validate(Object target, Errors errors) {
        MemoryForm memoryForm = (MemoryForm) target;

        if (memoryForm.getFiles().isEmpty()) {
            errors.rejectValue("files", "filesSizeZero");
        }
    }
}
