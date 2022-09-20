package com.family.petmemory.repository.member;

import com.family.petmemory.entity.Member;

import java.util.List;

public interface MemberRepository {

    Long save(Member member);

    Member findOne(Long id);

    List<Member> findAll();
}
