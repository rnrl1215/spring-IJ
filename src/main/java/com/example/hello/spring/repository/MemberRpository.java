package com.example.hello.spring.repository;

import com.example.hello.spring.domain.Member;

import java.util.List;
import java.util.Optional;

public interface MemberRpository {
    Member save(Member member);
    //Optional null을 반환하는 대신 Optional로 감싸서 반환
    Optional<Member> findById(Long id);
    Optional<Member> findByName(String name);
    List<Member> findAll();
}
