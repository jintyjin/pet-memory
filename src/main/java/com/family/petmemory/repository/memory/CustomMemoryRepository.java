package com.family.petmemory.repository.memory;

import com.family.petmemory.entity.dto.MemoryDetailForm;
import com.family.petmemory.entity.dto.MemorySearchCondition;
import com.family.petmemory.entity.memory.Memory;

import java.util.List;

public interface CustomMemoryRepository {
    List<Memory> search(MemorySearchCondition memorySearchCondition);

    MemoryDetailForm findMemoryDetail(Long memoryId);
}
