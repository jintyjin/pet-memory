package com.family.petmemory.repository.member;

import com.family.petmemory.entity.Member;
import lombok.RequiredArgsConstructor;

import javax.persistence.EntityManager;

@RequiredArgsConstructor
public class JpaMemberRepository implements MemberRepository {

    private final EntityManager em;

    @Override
    public Long save(Member member) {
        em.persist(member);
        return member.getId();
    }

    @Override
    public Member find(Long id) {
        return em.find(Member.class, id);
    }
}
