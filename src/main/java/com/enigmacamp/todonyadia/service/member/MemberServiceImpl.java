package com.enigmacamp.todonyadia.service.member;

import com.enigmacamp.todonyadia.dto.request.MemberRequest;
import com.enigmacamp.todonyadia.dto.response.MemberResponse;
import com.enigmacamp.todonyadia.entities.Member;
import com.enigmacamp.todonyadia.repository.MemberRepository;
import com.enigmacamp.todonyadia.utils.constants.ResponseMessage;
import com.enigmacamp.todonyadia.utils.exceptions.DataNotFoundException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
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
    public MemberResponse saveMember(MemberRequest payload) {
        Member member = Member.builder()
                .username(payload.username())
                .password(payload.password())
                .build();
        memberRepository.save(member);
        return member.toResponse();
    }

    @Override
    public Member saveMemberEntity(Member member) {
        return memberRepository.save(member);
    }


    @Override
    public Page<MemberResponse> getAllMember(Pageable pageable) {
        return memberRepository.findAll(pageable).map(Member::toResponse);
    }

    @Override
    public Boolean findByUsername(String username) {
        return memberRepository.findByUsername(username).isPresent();
    }

    @Override
    public MemberResponse getMemberById(UUID id) {
        Member member = memberRepository.findById(id)
            .orElseThrow(
                () -> new DataNotFoundException(String.format(ResponseMessage.NOT_FOUND_MESSAGE, ResponseMessage.MEMBER, id))
            );
        return member.toResponse();
    }

    @Override
    public MemberResponse updateMember(UUID id, MemberRequest memberUpdate) {
        Member member = memberRepository.findById(id)
            .orElseThrow(
                () -> new DataNotFoundException(String.format(ResponseMessage.NOT_FOUND_MESSAGE, ResponseMessage.MEMBER, id))
            );

        member.setUsername(memberUpdate.username());
        member.setPassword(memberUpdate.password());

        memberRepository.save(member);
        return member.toResponse();
    }

    @Override
    public void deleteMember(UUID id) {
        memberRepository.deleteById(id);
    }
}
