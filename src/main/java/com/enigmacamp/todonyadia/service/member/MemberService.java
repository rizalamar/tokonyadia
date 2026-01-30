package com.enigmacamp.todonyadia.service.member;


import com.enigmacamp.todonyadia.entities.Customer;
import com.enigmacamp.todonyadia.entities.Member;

import java.util.List;
import java.util.UUID;

public interface MemberService {
    Member saveMember(Member member);
    List<Member> getAllMember();
    Member getMemberById(UUID id);
    Member updateMember(UUID id, Member member);
    void deleteMember(UUID id);
}
