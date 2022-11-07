package com.family.petmemory.repository.pet;

import com.family.petmemory.entity.dto.QWeightForm;
import com.family.petmemory.entity.dto.WeightForm;
import com.family.petmemory.entity.pet.QWeight;
import com.querydsl.jpa.impl.JPAQueryFactory;

import javax.persistence.EntityManager;
import java.util.List;

import static com.family.petmemory.entity.pet.QWeight.weight1;

public class CustomWeightRepositoryImpl implements CustomWeightRepository {

    private final JPAQueryFactory jpaQueryFactory;

    public CustomWeightRepositoryImpl(EntityManager em) {
        this.jpaQueryFactory = new JPAQueryFactory(em);
    }

    @Override
    public List<WeightForm> findWeightForm(Long petId) {
        return jpaQueryFactory
                .select(new QWeightForm(weight1.id, weight1.date, weight1.weight))
                .from(weight1)
                .where(weight1.pet.id.eq(petId))
                .orderBy(weight1.date.asc())
                .fetch();
    }
}
