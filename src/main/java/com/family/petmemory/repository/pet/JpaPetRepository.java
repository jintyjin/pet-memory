package com.family.petmemory.repository.pet;

import com.family.petmemory.entity.member.Member;
import com.family.petmemory.entity.pet.Pet;
import lombok.RequiredArgsConstructor;

import javax.persistence.EntityManager;
import java.util.List;

@RequiredArgsConstructor
public class JpaPetRepository implements PetRepository {

    private final EntityManager em;

    @Override
    public Long save(Pet pet) {
        em.persist(pet);
        return pet.getId();
    }

    @Override
    public Pet findById(Long id) {
        return em.find(Pet.class, id);
    }

    @Override
    public List<Pet> findByMember(Member member) {
        return em.createQuery("select p from Pet p where p.member = :member")
                .setParameter("member", member)
                .getResultList();
    }

    @Override
    public List<Pet> findAll() {
        return em.createQuery("select p from Pet p")
                .getResultList();
    }
}
