package com.enigmacamp.todonyadia.service.member;

import com.enigmacamp.todonyadia.dto.request.MemberRequest;
import com.enigmacamp.todonyadia.entities.Member;
import com.enigmacamp.todonyadia.repository.MemberRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
public class MemberServiceImpl implements MemberService{
    private final MemberRepository memberRepository;

    public MemberServiceImpl(MemberRepository memberRepository) {
        this.memberRepository = memberRepository;
    }

    @Override
    public Member saveMember(MemberRequest payload) {
        Member member = Member.builder()
                .username(payload.username())
                .password(payload.password())
                .build();
        return memberRepository.save(member);
    }

    @Override
    public List<Member> getAllMember() {
        return memberRepository.findAll();
    }

    @Override
    public Member getMemberById(UUID id) {
        return memberRepository.findById(id).get();
    }

    @Override
    public Member updateMember(UUID id, MemberRequest memberUpdate) {
        Member member = Member.builder()
                .username(memberUpdate.username())
                .password(memberUpdate.password())
                .build();
        return memberRepository.save(member);
    }

    @Override
    public void deleteMember(UUID id) {
        memberRepository.deleteById(id);
    }
}
