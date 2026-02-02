package com.enigmacamp.todonyadia.service.member;

import com.enigmacamp.todonyadia.dto.request.MemberRequest;
import com.enigmacamp.todonyadia.dto.response.MemberResponse;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.UUID;

public interface MemberService {
    MemberResponse saveMember(MemberRequest member);
    Page<MemberResponse> getAllMember(Pageable pageable);
    MemberResponse getMemberById(UUID id);
    MemberResponse updateMember(UUID id, MemberRequest member);
    void deleteMember(UUID id);
}
