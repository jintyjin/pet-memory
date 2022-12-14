package com.family.petmemory.repository.memory;

import com.family.petmemory.entity.dto.MemorySearchCondition;
import com.family.petmemory.entity.memory.Memory;
import com.family.petmemory.entity.memory.MemoryStatus;
import com.family.petmemory.entity.memory.MemoryType;
import com.querydsl.core.types.Predicate;
import com.querydsl.core.types.dsl.BooleanExpression;
import com.querydsl.jpa.impl.JPAQueryFactory;

import javax.persistence.EntityManager;
import java.util.List;

import static com.family.petmemory.entity.memory.QMemory.memory;

public class CustomMemoryRepositoryImpl implements CustomMemoryRepository {

    private final int MAX_COUNT = 50;

    private final JPAQueryFactory jpaQueryFactory;

    public CustomMemoryRepositoryImpl(EntityManager em) {
        this.jpaQueryFactory = new JPAQueryFactory(em);
    }

    @Override
    public List<Memory> search(MemorySearchCondition condition) {
        return jpaQueryFactory
                .selectFrom(memory)
                .where(
                        petIdEq(condition.getPetId()),
                        memoryIdEq(condition.getMemoryId()),
                        memoryStatusEq(condition.getMemoryStatus()),
                        memoryTypeEq(condition.getMemoryType())
                )
                .limit(MAX_COUNT)
                .orderBy(memory.manageTime.uploadTime.desc(), memory.id.asc())
                .fetch();
    }

    private BooleanExpression memoryTypeEq(MemoryType memoryType) {
        return memoryType == null ? null : memory.memoryType.eq(memoryType);
    }

    private BooleanExpression memoryIdEq(Long memoryId) {
        return memoryId == null ? null : memory.id.eq(memoryId);
    }

    private BooleanExpression petIdEq(Long petId) {
        return petId == null ? null : memory.pet.id.eq(petId);
    }

    private BooleanExpression memoryStatusEq(MemoryStatus memoryStatus) {
        return memoryStatus == null ? null : memory.memoryStatus.eq(memoryStatus);
    }


}
