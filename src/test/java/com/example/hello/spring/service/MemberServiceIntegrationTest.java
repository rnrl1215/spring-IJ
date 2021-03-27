package com.example.hello.spring.service;

import com.example.hello.spring.domain.Member;
import com.example.hello.spring.repository.MemberRepository;
import com.example.hello.spring.repository.MemoryMemberRepository;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import static org.assertj.core.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest // 스프링 컨테이너와 테스트를 함께 실행한다.
@Transactional // Roll back 함. 즉 계속해서 TEST 가능함.
class MemberServiceIntegrationTest {

    // test code 면 필드 기반으로 해도됨.
    @Autowired MemberService memberService;
    @Autowired MemberRepository memberRepository;

    @Test
    void join() {
        //given when then 문법

        //given
        Member member = new Member();
        member.setName("hello");

        //when
        Long SaveId = memberService.join(member);

        //then
        Member findMember = memberService.findOne(SaveId).get();
        assertThat(member.getName()).isEqualTo(findMember.getName());
    }

    @Test
    void findMemberes() {
        //given
        Member member1 = new Member();
        member1.setName("spring1");

        Member member2 = new Member();
        member2.setName("spring2");

        //when
        memberService.join(member1);
        IllegalStateException e = assertThrows(IllegalStateException.class, () -> memberService.join(member2));
        assertThat(e.getMessage()).isEqualTo("이미 존재하는 회원입니다.");

    }

}