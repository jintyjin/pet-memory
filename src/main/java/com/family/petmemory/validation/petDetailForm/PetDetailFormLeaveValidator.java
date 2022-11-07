package com.family.petmemory.validation.petDetailForm;

import com.family.petmemory.entity.dto.PetDetailForm;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;

import java.time.LocalDate;

@Component
public class PetDetailFormLeaveValidator extends PetDetailFormValidator {

    @Override
    public void validate(Object target, Errors errors) {
        PetDetailForm petDetailForm = (PetDetailForm) target;

        LocalDate bornTime = petDetailForm.getBornTime();
        LocalDate leaveTime = petDetailForm.getLeaveTime();

        if (leaveTime == null) {
            errors.rejectValue("leaveTime", "leaveTimeNull");
        } else if (bornTime.isAfter(leaveTime)) {
            errors.rejectValue("leaveTime", "canNotBeforeBornTime");
        } else if (leaveTime.isAfter(LocalDate.now())) {
            errors.rejectValue("leaveTime", "canNotAfterToday");
        }
    }
}
