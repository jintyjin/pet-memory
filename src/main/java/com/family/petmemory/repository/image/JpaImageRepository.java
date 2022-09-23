package com.family.petmemory.repository.image;

import com.family.petmemory.entity.image.Image;
import com.family.petmemory.entity.pet.Pet;
import lombok.AllArgsConstructor;

import javax.persistence.EntityManager;
import java.util.List;

@AllArgsConstructor
public class JpaImageRepository implements ImageRepository {

    private final EntityManager em;

    @Override
    public Long save(Image image) {
        em.persist(image);
        return image.getId();
    }

    @Override
    public Image findById(Long id) {
        return em.find(Image.class, id);
    }

    @Override
    public List<Image> findByPet(Pet pet) {
        return em.createQuery("select i from Image i where i.pet = :pet")
                .setParameter("pet", pet)
                .getResultList();
    }

    @Override
    public List<Image> findAll() {
        return em.createQuery("select i from Image i")
                .getResultList();
    }
}
