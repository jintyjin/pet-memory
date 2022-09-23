package com.family.petmemory.repository.member;

import com.family.petmemory.entity.member.Member;
import com.family.petmemory.entity.member.MemberStatus;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Rollback;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

import static org.assertj.core.api.Assertions.*;

@SpringBootTest
class JpaMemberRepositoryTest {

    @Autowired
    MemberRepository memberRepository;

    @Test
    @Transactional
    @Rollback(value = false)
    public void 회원가입() {
        //given
        Member member = new Member("memberA", "주인1", "암호");
        Member member2 = new Member("memberB", "주인2", "암호1");

        //when
        Long savedId = memberRepository.save(member);
        memberRepository.save(member2);
        Member findMember = memberRepository.findById(savedId);

        //then
        assertThat(findMember.getId()).isEqualTo(member.getId());
        assertThat(findMember.getName()).isEqualTo(member.getName());
    }

    @Test
    @Transactional
    public void 회원검색() {
        //given
        Member member = new Member("memberA", "주인1", "암호");
        memberRepository.save(member);

        //when
        Optional<Member> findMember = memberRepository.findByLoginId(member.getLoginId());

        //then
        assertThat(member.getLoginId()).isEqualTo(findMember.get().getLoginId());
    }

    @Test
    @Transactional
    public void 회원삭제() {
        //given
        Member member = new Member("memberA", "주인1", "암호");
        memberRepository.save(member);

        //when
        member.delete();

        //then
        assertThat(member.getMemberStatus()).isEqualTo(MemberStatus.DELETE);
    }
}