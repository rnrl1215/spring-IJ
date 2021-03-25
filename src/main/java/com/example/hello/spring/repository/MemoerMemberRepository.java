package com.example.hello.spring.repository;

import com.example.hello.spring.domain.Member;

import java.util.*;

public class MemoerMemberRepository implements MemberRpository {

    private static Map<Long, Member> store = new HashMap<>();
    private static long squence = 0L;

    @Override
    public Member save(Member member) {
        member.setId(++squence);
        store.put(member.getId(), member);
        return null;
    }

    @Override
    public Optional<Member> findById(Long id) {
        return Optional.ofNullable(store.get(id));
    }

    @Override
    public Optional<Member> findByName(String name) {
        return store.values().stream()
                .filter(member -> member.getName().equals(name))
                .findAny();
    }

    @Override
    public List<Member> findAll() {
        return new ArrayList<>(store.values());
    }
}
