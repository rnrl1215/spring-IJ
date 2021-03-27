package com.example.hello.spring;

import com.example.hello.spring.repository.MemberRepository;
import com.example.hello.spring.repository.MemoryMemberRepository;
import com.example.hello.spring.repository.jdbcMemberRepository;
import com.example.hello.spring.service.MemberService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import javax.sql.DataSource;

@Configuration //
public class SpringConfig {

    // jdbc로 변경후 data source DI
    private DataSource dataSource;

    @Autowired
    public SpringConfig(DataSource dataSource)
    {
        this.dataSource = dataSource;
    }

    @Bean //spring bean을 등록 할거라는 것을 표시
          // 스프링 빈이 뜰때 이것을 읽고 어 등록 하라는거네? 라고 인식
    public MemberService memberService() {
        // 이게 호출 되어 등록된게 올라옴.
        return new MemberService(memberRpository());
    }

    @Bean
    public MemberRepository memberRpository() {
        //return new MemoryMemberRepository();
        return new jdbcMemberRepository(dataSource);
    }
    // 스프링이 뜰때 두개를 스프링빈에 등록한다
    // 등록된 멤버 리파지토리를 넣어준다
}
