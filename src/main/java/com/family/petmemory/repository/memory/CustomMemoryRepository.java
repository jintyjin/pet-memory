package com.family.petmemory.repository.memory;

import com.family.petmemory.entity.dto.MemoryDetailForm;
import com.family.petmemory.entity.dto.MemorySearchCondition;
import com.family.petmemory.entity.dto.MemoryWalkForm;
import com.family.petmemory.entity.dto.MemoryWalkInfoDto;
import com.family.petmemory.entity.memory.Memory;

import java.util.List;

public interface CustomMemoryRepository {
    List<Memory> search(MemorySearchCondition memorySearchCondition);

    MemoryDetailForm findMemoryDetail(Long memoryId);

    List<MemoryWalkForm> findMemoryWalk(Long petId);

    MemoryWalkInfoDto findMemoryWalkInfo(Long memoryId);
}
