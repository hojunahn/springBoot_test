package com.example.TestSpringBoot_20240610.controller;

import com.example.TestSpringBoot_20240610.dto.MemberDto;
import com.example.TestSpringBoot_20240610.service.UserService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@Slf4j
@CrossOrigin(origins = "http://localhost:3000")
@RestController
@RequestMapping("/auth")
@RequiredArgsConstructor
public class UserController {
    private final UserService userService;

    // 회원 가입 여부 확인
    @GetMapping("/exists/{email}")
    public ResponseEntity<Boolean> memberExists(@PathVariable String email){
        log.info("email: {}", email);
        boolean isTrue = userService.isMember(email);
        return ResponseEntity.ok(!isTrue);
    }

    // 회원가입
    @PostMapping("/signUp")
    public ResponseEntity<Boolean> signUp(@RequestBody MemberDto memberDto){
        log.info("memberDto: {}", memberDto.getEmail());
        log.info("memberDto: {}", memberDto.getPwd());
        boolean isTrue = userService.signUp(memberDto);
        return ResponseEntity.ok(isTrue);
    }

    // 로그인
    @PostMapping("/login")
    public ResponseEntity<Boolean> login(@RequestBody MemberDto memberDto){
        log.info("memberDto: {}", memberDto.getEmail());
        log.info("memberDto: {}", memberDto.getPwd());
        boolean isTrue = userService.login(memberDto.getEmail(), memberDto.getPwd());
        return ResponseEntity.ok(isTrue);
    }
}
