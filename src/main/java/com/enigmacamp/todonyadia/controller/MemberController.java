package com.enigmacamp.todonyadia.controller;

import com.enigmacamp.todonyadia.dto.request.MemberRequest;
import com.enigmacamp.todonyadia.dto.response.MemberResponse;
import com.enigmacamp.todonyadia.entities.Member;
import com.enigmacamp.todonyadia.service.member.MemberService;
import com.enigmacamp.todonyadia.utils.constants.ApiUrlConstants;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
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
    public ResponseEntity <MemberResponse> addMember(@RequestBody MemberRequest payload){
        MemberResponse memberResponse = memberService.saveMember(payload);
        return ResponseEntity.status(HttpStatus.CREATED).body(memberResponse);
    }

    @GetMapping()
    public Page<MemberResponse> getAllMember(
        @RequestParam(name = "page", defaultValue = "0") int page,
        @RequestParam(name = "size", defaultValue = "3") int size
    ){
        Pageable pageable = PageRequest.of(page, size);
        return memberService.getAllMember(pageable);
    }

    @GetMapping("/{id}")
    public MemberResponse getMemberById(@PathVariable UUID id){
        return memberService.getMemberById(id);
    }

    @PutMapping("/{id}")
    public MemberResponse updateMember(@PathVariable UUID id, @RequestBody MemberRequest member){
        return memberService.updateMember(id, member);
    }

    @DeleteMapping("/{id}")
    public void deleteMember(@PathVariable UUID id){
        memberService.deleteMember(id);
    }
}
