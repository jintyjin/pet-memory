package com.family.petmemory.repository.memory;

import com.family.petmemory.entity.memory.Memory;
import com.family.petmemory.entity.pet.Pet;
import lombok.AllArgsConstructor;

import javax.persistence.EntityManager;
import java.util.List;

@AllArgsConstructor
public class JpaMemoryRepository implements MemoryRepository {

    private final EntityManager em;

    @Override
    public Long save(Memory memory) {
        em.persist(memory);
        return memory.getId();
    }

    @Override
    public Memory findById(Long id) {
        return em.find(Memory.class, id);
    }

    @Override
    public List<Memory> findByPet(Pet pet) {
        return em.createQuery("select m from memory m where m.pet = :pet")
                .setParameter("pet", pet)
                .getResultList();
    }

    @Override
    public List<Memory> findAll() {
        return em.createQuery("select m from memory m")
                .getResultList();
    }
}
