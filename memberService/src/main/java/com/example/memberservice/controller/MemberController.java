package com.example.memberservice.controller;

import com.example.memberservice.Entity.MemberEntity;
import com.example.memberservice.dto.JwtToken;
import com.example.memberservice.dto.SignInDto;
import com.example.memberservice.dto.MemberDto;
import com.example.memberservice.service.MemberService;

import com.example.memberservice.vo.RequestMember;
import com.example.memberservice.vo.ResponseMember;
import lombok.extern.slf4j.Slf4j;
import org.springframework.core.env.Environment;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@Slf4j
@RestController
@RequestMapping("/member-service")
public class MemberController {
    private final Environment env;
    private final MemberService memberService;

    public MemberController(Environment env, MemberService memberService) {
        this.env = env;
        this.memberService = memberService;
    }
    @GetMapping("health_check")
    public String status() {
        return String.format("Ok on Port = %s", env.getProperty("local.server.port"));
    }

    @PostMapping("/create")
    public ResponseEntity<ResponseMember> createUser(@RequestBody RequestMember member) {
        MemberDto memberDto = new MemberDto();
        memberDto.setEmail(member.getEmail());
        memberDto.setName(member.getName());
        memberDto.setPw(member.getPw());
        memberService.createUser(memberDto);

        ResponseMember responseMember = new ResponseMember();
        responseMember.setUserId(memberDto.getUserId());
        responseMember.setName(memberDto.getName());
        responseMember.setEmail(memberDto.getEmail());
        return new ResponseEntity(responseMember,HttpStatus.CREATED);
    }

    @PostMapping("/login")
    public ResponseEntity<JwtToken> login(@RequestBody SignInDto signInDto) {
        log.info("signInDto = {}", signInDto.getPassword());
        String email = signInDto.getEmail();
        String password = signInDto.getPassword();
        JwtToken jwtToken = memberService.signIn(email, password);
        log.info("jwtToken = {}", jwtToken);
        log.info("request email = {}, password = {}", email, password);
        log.info("jwtToken accessToken = {}", jwtToken.getAccessToken());
        return new ResponseEntity(jwtToken, HttpStatus.OK);
    }

    @GetMapping("/id/{id}")
    public ResponseEntity<MemberEntity> getUserById(@PathVariable("id") Long id) {
        Optional<MemberEntity> member = memberService.getMemberById(id);
        return new ResponseEntity(member, HttpStatus.OK);
    }

    @GetMapping("/{userId}")
    public ResponseEntity<MemberEntity> getUserbyUserId(@PathVariable("userId") String userId) {
        Optional<MemberEntity> member = memberService.getUserByUserId(userId);
        return new ResponseEntity(member, HttpStatus.OK);
    }

    @GetMapping("/all")
    public ResponseEntity<MemberEntity> getAllMembers() {
        List<MemberEntity> member = memberService.getAllMembers();
        return new ResponseEntity(member, HttpStatus.OK);
    }

}
