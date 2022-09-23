package com.family.petmemory.repository.member;

import com.family.petmemory.entity.member.Member;

import java.util.List;
import java.util.Optional;

public interface MemberRepository {

    Long save(Member member);

    Member findById(Long id);

    Optional<Member> findByLoginId(String loginId);

    List<Member> findByName(String name);

    List<Member> findAll();
}
