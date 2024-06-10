package com.example.TestSpringBoot_20240610.service;

import com.example.TestSpringBoot_20240610.dto.MemberDto;
import com.example.TestSpringBoot_20240610.entity.Member;
import com.example.TestSpringBoot_20240610.repository.MemberRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.Optional;

@Slf4j
@Service
@RequiredArgsConstructor
@Transactional
public class UserService {
    private final MemberRepository memberRepository;

    // 회원 가입 여부 확인
    public boolean isMember(String email) {
        return memberRepository.existsByEmail(email);
    }
    // 회원 가입
    public boolean signUp(MemberDto memberDto){
        try {
            Member member = convertDtoToEntity(memberDto);
            memberRepository.save(member);
            return true;

        }catch (Exception e){
            log.error("Error occurred during signup: {}",e.getMessage(), e);
            return false;
        }
    }
    // 로그인
    public boolean login(String email, String pwd){
        Optional<Member> member = memberRepository.findByEmailAndPwd(email,pwd);
        log.info("member: {}", member);
        return member.isPresent();
    }

    // 회원 Dto -> Entity
    private Member convertDtoToEntity(MemberDto memberDto){
        Member member = new Member();
        member.setEmail(memberDto.getEmail());
        member.setPwd(memberDto.getPwd());
        member.setName(memberDto.getName());
        member.setImg(memberDto.getImg());
        return member;
    }
}
