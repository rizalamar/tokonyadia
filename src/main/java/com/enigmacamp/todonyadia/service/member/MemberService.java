package com.enigmacamp.todonyadia.service.member;

import com.enigmacamp.todonyadia.dto.request.MemberRequest;
import com.enigmacamp.todonyadia.dto.response.MemberResponse;
import com.enigmacamp.todonyadia.entities.Member;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.UUID;

public interface MemberService {
    MemberResponse saveMember(MemberRequest member);
    Member saveMemberEntity(Member member);
    Page<MemberResponse> getAllMember(Pageable pageable);
    Boolean findByUsername(String username);
    MemberResponse getMemberById(UUID id);
    MemberResponse updateMember(UUID id, MemberRequest member);
    void deleteMember(UUID id);
}
