package com.family.petmemory.service;

import com.family.petmemory.entity.member.Member;
import com.family.petmemory.repository.member.MemberRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class MemberService {

    private final MemberRepository memberRepository;

    @Transactional
    public Long join(Member member) {
        validateDuplicateMember(member);
        memberRepository.save(member);
        return member.getId();
    }

    private void validateDuplicateMember(Member member) {
        Optional<Member> loginMember = memberRepository.findByLoginId(member.getLoginId());
        if (loginMember.isPresent()) {
            throw new IllegalStateException("이미 존재하는 아이디입니다.");
        }

        List<Member> nameMember = memberRepository.findByName(member.getName());
        if (nameMember.size() > 0 ) {
            throw new IllegalStateException("이미 존재하는 사용자입니다.");
        }
    }

    public List<Member> findMembers() {
        return memberRepository.findAll();
    }

    public Member findMember(Long memberId) {
        return memberRepository.findById(memberId);
    }
}
