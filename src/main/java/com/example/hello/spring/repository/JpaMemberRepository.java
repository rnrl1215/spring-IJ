package com.example.hello.spring.repository;

import com.example.hello.spring.domain.Member;

import javax.persistence.EntityManager;
import java.util.List;
import java.util.Optional;

public class JpaMemberRepository implements MemberRepository{

    // JPA 모든 동작을 EntityManager 로 동작함.
    private final EntityManager em;
    
    // 스프링 부트가 EntityManager를 자동으로 만들어줌
    public JpaMemberRepository(EntityManager em) {
        this.em = em;
    }

    @Override
    public Member save(Member member) {
        em.persist(member);
        return member;
    }

    @Override
    public Optional<Member> findById(Long id) {
        // 조회가 됨
        Member member = em.find(Member.class, id);
        return Optional.ofNullable(member);
    }

    @Override
    public Optional<Member> findByName(String name) {
        // find by name은 jpql을 써야함.
        List<Member> result = em.createQuery("select m from Member m where m.name = :name", Member.class)
                .setParameter("name", name)
                .getResultList();
        // 하나만 찾기 때문에 findAny();
        return result.stream().findAny();
    }

    @Override
    public List<Member> findAll() {

        // 해당 소스를 아래처럼 인라인으로 만들수 있음.
        // 단 아래처럼 리턴 값과 변수값이 같을 경우 가능
        // 단축키 alt+ctrl+n

        /* 
        List<Member> result =  em.createQuery("select m from Member m", Member.class).getResultList();
        return result;
        */

        // 객체를 대상으로 쿼리를 날림.
        // select m 은 member entity 를 선택함.
        return em.createQuery("select m from Member m", Member.class)
                .getResultList();
    }
}
