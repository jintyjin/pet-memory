package com.family.petmemory.repository.memory;

import com.family.petmemory.entity.dto.*;
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

    @Override
    public MemoryDetailForm findMemoryDetail(Long memoryId) {
        return jpaQueryFactory
                .select(new QMemoryDetailForm(
                        memory.id, memory.info, memory.manageTime.imageTime,
                        memory.uploadFile, memory.imageSize, memory.gps
                ))
                .from(memory)
                .where(memory.id.eq(memoryId))
                .fetchOne();
    }

    @Override
    public List<MemoryWalkForm> findMemoryWalk(Long petId) {
        return jpaQueryFactory
                .select(new QMemoryWalkForm(
                        memory.id, memory.uploadFile.saveFileName, memory.manageTime.imageTime,
                        memory.imageSize, memory.gps, memory.memoryStatus, memory.memoryType
                ))
                .from(memory)
                .where(
                        memory.pet.id.eq(petId),
                        memory.memoryType.eq(MemoryType.IMAGE),
                        memory.gps.isNotNull()
                )
                .orderBy(memory.manageTime.imageTime.asc())
                .fetch();
    }

    @Override
    public MemoryWalkInfoDto findMemoryWalkInfo(Long memoryId) {
        return jpaQueryFactory
                .select(
                        new QMemoryWalkInfoDto(
                                memory.id, memory.info, memory.manageTime.imageTime, memory.uploadFile, memory.imageSize
                        )
                )
                .from(memory)
                .where(memory.id.eq(memoryId))
                .fetchOne();
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
