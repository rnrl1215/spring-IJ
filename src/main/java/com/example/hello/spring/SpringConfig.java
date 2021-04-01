package com.example.hello.spring;

import com.example.hello.spring.repository.*;
import com.example.hello.spring.service.MemberService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import javax.persistence.EntityManager;

@Configuration //
public class SpringConfig {


    /* JPA 로 변경하면서 아래 내용 필요없음.
    // jdbc로 변경후 data source DI
    private DataSource dataSource;

    @Autowired
    public SpringConfig(DataSource dataSource)
    {
        this.dataSource = dataSource;
    }
    */

    private EntityManager em;

    @Autowired
    public SpringConfig(EntityManager em) {
        this.em = em;
    }

    @Bean //spring bean을 등록 할거라는 것을 표시
          // 스프링 빈이 뜰때 이것을 읽고 어 등록 하라는거네? 라고 인식
    public MemberService memberService() {
        // 이게 호출 되어 등록된게 올라옴.
        return new MemberService(memberRpository());
    }

    @Bean
    public MemberRepository memberRpository() {
        // Memory에서 DB를 사용하는 구조로 변경
        // 내부 코드를 변경할 필요 없이 해당 내용으로 변경하면
        // 스프링에서 알아서 변경해서 작동함.

        // 1 메모리 저장
        //return new MemoryMemberRepository();
        
        // 2 jdbc 로저장
        //return new jdbcMemberRepository(dataSource);
         
        // 3jdbcTemplateMemberRepository 로변경
        //return new jdbcTemplateMemberRepository(dataSource);

        // 4. jpa 로 변경 Entity manager 필요함.
        return new JpaMemberRepository(em);

    }
    // 스프링이 뜰때 두개를 스프링빈에 등록한다
    // 등록된 멤버 리파지토리를 넣어준다
}
