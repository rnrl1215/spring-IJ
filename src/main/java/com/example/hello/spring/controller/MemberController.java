package com.example.hello.spring.controller;

import com.example.hello.spring.service.MemberService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

@Controller
// 스프링이 올라 올때 스프링이 애노테이션을 보고 객체를 생성해서 들고 있음
// 스프링 빈이 관리된다고 보면됨.
public class MemberController {

    // 이건 한번만 만들면된
    // 스프링에 등록하면 관리하기 쉽다.
    private final MemberService memberService;

    @Autowired //멤버 서비스를 스프링이 스프링 컨텐이너의 멤버서비스를 가져다 연결 시킨다.
    public MemberController(MemberService memberService) {
        this.memberService = memberService;
    }
}
