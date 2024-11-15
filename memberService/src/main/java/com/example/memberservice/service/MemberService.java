package com.example.memberservice.service;

import com.example.memberservice.Entity.MemberEntity;
import com.example.memberservice.dto.JwtToken;
import com.example.memberservice.dto.MemberDto;
import com.example.memberservice.repository.MemberRepository;
import org.springframework.beans.factory.annotation.Autowired;

import java.lang.reflect.Member;
import java.util.List;
import java.util.Optional;

public interface MemberService {

    JwtToken signIn(String email, String password);
    MemberDto createUser(MemberDto userDto);
    Optional<MemberEntity> getUserByUserId(String userId);
    Optional<MemberEntity> getMemberById(Long id);
    List<MemberEntity> getAllMembers();
}
