package com.family.petmemory.repository.pet;

import com.family.petmemory.entity.dto.PetDetailForm;
import com.family.petmemory.entity.dto.PetProfileForm;
import com.family.petmemory.entity.dto.QPetDetailForm;
import com.family.petmemory.entity.dto.QPetProfileForm;
import com.family.petmemory.entity.member.Member;
import com.family.petmemory.entity.pet.QWeight;
import com.querydsl.core.types.dsl.Expressions;
import com.querydsl.jpa.impl.JPAQueryFactory;

import javax.persistence.EntityManager;

import java.util.List;

import static com.family.petmemory.entity.memory.QMemory.*;
import static com.family.petmemory.entity.pet.QPet.*;

public class CustomPetRepositoryImpl implements CustomPetRepository {

    private final JPAQueryFactory jpaQueryFactory;

    public CustomPetRepositoryImpl(EntityManager em) {
        this.jpaQueryFactory = new JPAQueryFactory(em);
    }

    @Override
    public PetProfileForm findPetProfile(Long petId) {
        return jpaQueryFactory
                .select(new QPetProfileForm(pet.id,
                                pet.name,
                                memory.uploadFile.saveFileName,
                                memory.memoryType
                        )
                )
                .from(pet)
                .where(
                        pet.id.eq(petId)
                )
                .leftJoin(memory).on(pet.profile.eq(memory.id))
                .fetchOne();
    }

    @Override
    public List<PetProfileForm> findPetProfiles(Member member) {
        return jpaQueryFactory
                .select(new QPetProfileForm(pet.id,
                        pet.name,
                        memory.uploadFile.saveFileName,
                        memory.memoryType
                        )
                )
                .from(pet)
                .where(
                        pet.member.id.eq(member.getId())
                )
                .leftJoin(memory).on(pet.profile.eq(memory.id))
                .fetch();
    }

    @Override
    public PetDetailForm findPetDetail(Long petId) {
        return jpaQueryFactory
                .select(new QPetDetailForm(
                        pet,
                        memory.uploadFile.saveFileName,
                        memory.memoryType
                ))
                .from(pet)
                .where(pet.id.eq(petId))
                .leftJoin(memory)
                .on(
                        pet.id.eq(memory.pet.id),
                        pet.profile.eq(memory.id)
                )
                .fetchOne();
    }

}
