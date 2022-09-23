package com.family.petmemory.repository.image;

import com.family.petmemory.entity.image.Image;
import com.family.petmemory.entity.pet.Pet;

import java.util.List;

public interface ImageRepository {

    Long save(Image image);

    Image findById(Long id);

    List<Image> findByPet(Pet pet);

    List<Image> findAll();
}
