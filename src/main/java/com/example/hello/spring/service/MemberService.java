package com.example.hello.spring.service;

import com.example.hello.spring.domain.Member;
import com.example.hello.spring.repository.MemberRepository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;


//JPA 를 사용할 경우 항상 트랜잭션이 있어야함.
@Transactional
public class MemberService {

    private final MemberRepository memberRepository;



    public MemberService(MemberRepository memberRepository) {
       this.memberRepository = memberRepository;
    }

    /**
     * 회원가입
     */
    public Long join(Member member){
        // 중복 회원 안됨

        // 값이 존재 하면 아래 수행
        // optional 이라 가능
        // optional 이 아니면 null 검사 수행
        // 예전에는 null check 를 했지만 최근에는
        // null 이 존재하는 경우 optional을 사용
        // get으로 내용을 꺼낼수 있음.
        // orElseGet 을 수행함.
        /** 코드 개선 result를 반환 안하고 수행
         Optional<Member> result = memberRpository.findByName(member.getName());
         */

        validateDuplicateMember(member);
        memberRepository.save(member);
        return member.getId();
    }

    private void validateDuplicateMember(Member member) {
        memberRepository.findByName(member.getName())
                .ifPresent(m -> {
                    throw new IllegalStateException("이미 존재하는 회원입니다.");
                });
    }

    /**
     * 전체 회원 조회
     */
    public List<Member> findMembers() {
       return  memberRepository.findAll();
    }

    public Optional<Member> findOne(Long memberId){
        return memberRepository.findById(memberId);
    }
}
