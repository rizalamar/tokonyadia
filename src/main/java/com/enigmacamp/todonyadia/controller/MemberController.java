package com.enigmacamp.todonyadia.controller;

import com.enigmacamp.todonyadia.dto.request.MemberRequest;
import com.enigmacamp.todonyadia.entities.Member;
import com.enigmacamp.todonyadia.service.member.MemberService;
import com.enigmacamp.todonyadia.utils.constants.ApiUrlConstants;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping(ApiUrlConstants.MEMBER)
public class MemberController {
    private MemberService memberService;

    public MemberController(MemberService memberService) {
        this.memberService = memberService;
    }

    @PostMapping()
    public Member addMember(@RequestBody MemberRequest member){
        return memberService.saveMember(member);
    }

    @GetMapping()
    public List<Member> getAllMember(){
        return memberService.getAllMember();
    }

    @GetMapping("/{id}")
    public Member getMemberById(@PathVariable UUID id){
        return memberService.getMemberById(id);
    }

    @PutMapping("/{id}")
    public Member updateMember(@PathVariable UUID id, @RequestBody MemberRequest member){
        return memberService.updateMember(id, member);
    }

    @DeleteMapping("/{id}")
    public void deleteMember(@PathVariable UUID id){
        memberService.deleteMember(id);
    }
}
