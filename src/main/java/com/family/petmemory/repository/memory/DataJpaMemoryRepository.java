package com.family.petmemory.repository.memory;

import com.family.petmemory.entity.memory.Memory;
import org.springframework.data.jpa.repository.JpaRepository;

public interface DataJpaMemoryRepository extends JpaRepository<Memory, Long>, CustomMemoryRepository {
}
