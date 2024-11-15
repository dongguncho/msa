package com.example.memberservice.service;

import com.example.memberservice.Entity.MemberEntity;
import com.example.memberservice.dto.JwtToken;
import com.example.memberservice.dto.MemberDto;
import com.example.memberservice.repository.MemberRepository;
import com.example.memberservice.security.JwtUtil;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Slf4j
@Service
@RequiredArgsConstructor
public class MemberServiceImpl implements MemberService {
  @Autowired
  private JwtUtil jwtUtil;
  private final MemberRepository memberRepository;

  @Override
  public MemberDto createUser(MemberDto memberDto) {
    memberDto.setUserId(UUID.randomUUID().toString());
    MemberEntity memberEntity = new MemberEntity();
    memberEntity.setUserId(memberDto.getUserId());
    memberEntity.setEmail(memberDto.getEmail());
    memberEntity.setName(memberDto.getName());
    memberEntity.setEncryptedPw(memberDto.getPw());
    memberEntity.setPassword(memberDto.getPw());
    memberEntity.setRole("USER");
    memberRepository.save(memberEntity);
    return null;
  }

  @Override
  public JwtToken signIn(String email, String password) {
    log.info("username = {}, password = {}", email, password);
    log.info("jwtToken = {}", jwtUtil.generateToken(email));
    MemberEntity memberEntity = memberRepository.findByEmail(email);
    if (memberEntity != null && !memberEntity.getPassword().equals(password)) {
      return JwtToken
          .builder()
          .grantType("Bearer")
          .accessToken(jwtUtil.generateToken(email))
          .build();
    }else{
      throw new IllegalArgumentException("존재하지 않는 사용자입니다.");
    }
  }
  @Override
  public Optional<MemberEntity> getUserByUserId(String userId) {
    Optional<MemberEntity> member = memberRepository.findByUserId(userId);
    log.info("memberEntity = {}", member);
    return member;
  }

  @Override
  public Optional<MemberEntity> getMemberById(Long id) {
    Optional<MemberEntity> member =  memberRepository.findById(id);
    if(member.isEmpty()){
      throw new RuntimeException("No member found with id: " + id);
    }else{
      return member;
    }
  }

  @Override
  public List<MemberEntity> getAllMembers() {
    return memberRepository.findAll();
  }
}
