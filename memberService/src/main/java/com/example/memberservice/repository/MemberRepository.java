package com.example.memberservice.repository;

import com.example.memberservice.Entity.MemberEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface MemberRepository extends JpaRepository<MemberEntity, Long> {
  Optional<MemberEntity> findByUserId(String userId);
  MemberEntity findByEmail(String email);
}
