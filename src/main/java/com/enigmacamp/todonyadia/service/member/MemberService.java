package com.enigmacamp.todonyadia.service.member;


import com.enigmacamp.todonyadia.dto.request.MemberRequest;
import com.enigmacamp.todonyadia.entities.Customer;
import com.enigmacamp.todonyadia.entities.Member;

import java.util.List;
import java.util.UUID;

public interface MemberService {
    Member saveMember(MemberRequest member);
    List<Member> getAllMember();
    Member getMemberById(UUID id);
    Member updateMember(UUID id, MemberRequest member);
    void deleteMember(UUID id);
}
