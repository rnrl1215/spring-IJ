package com.example.hello.spring.repositiry;

import com.example.hello.spring.domain.Member;
import com.example.hello.spring.repository.MemoerMemberRepository;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.*;

class MemoryMemberRepositoryTest {
    MemoerMemberRepository repository = new MemoerMemberRepository();

    // test code 작성
    @Test
    public void save(){
        Member member = new Member();
        member.setName("spring");

        repository.save(member);

        Member result = repository.findById(member.getId()).get();
        assertThat(member).isEqualTo(result);
    }

}
