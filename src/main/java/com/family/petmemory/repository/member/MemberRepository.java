package com.family.petmemory.repository.member;

import com.family.petmemory.entity.Member;

public interface MemberRepository {

    Long save(Member member);

    Member find(Long id);

}
