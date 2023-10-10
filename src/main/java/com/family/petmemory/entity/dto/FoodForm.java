package com.family.petmemory.entity.dto;

import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.web.multipart.MultipartFile;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;
import java.util.List;

@Data
@NoArgsConstructor
public class FoodForm {

    @NotEmpty
    @Size(min = 2, max = 20)
    @Pattern(regexp = "^[A-Za-z][가-힣]{1,10}$")
    private String name;

    private List<RecipeForm> recipeForms;

    private MultipartFile thumbnail;
}
