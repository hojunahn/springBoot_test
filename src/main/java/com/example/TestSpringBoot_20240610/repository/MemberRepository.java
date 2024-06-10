package com.example.TestSpringBoot_20240610.repository;

import com.example.TestSpringBoot_20240610.dto.MemberDto;
import com.example.TestSpringBoot_20240610.entity.Member;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface MemberRepository extends JpaRepository<Member, Long> {
    Optional<Member> findByEmail(String email);
    Optional<Member> findByEmailAndPwd(String email, String pwd);

    // 이메일 존재 여부
    boolean existsByEmail(String email);

}
