package com.example.hello.spring.controller;

import com.example.hello.spring.domain.Member;
import com.example.hello.spring.service.MemberService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

// 스프링이 올라 올때 스프링이 애노테이션을 보고 객체를 생성해서 들고 있음
// 스프링 빈이 관리된다고 보면됨.
@Controller // 스프링이 어차피 관리 하기 때문에 그냥 지우지 않고 둔다
public class MemberController {

    // 이건 한번만 만들면된
    // 스프링에 등록하면 관리하기 쉽다.

    // 생성자 주입1: 생성자를 통해서 멤버서비스가 멤버 컨트롤러에 들어오는것
    // 가장 많이 쓰는 방법
    private final MemberService memberService;
    @Autowired //멤버 서비스를 스프링이 스프링 컨텐이너의 멤버서비스를 가져다 연결 시킨다.
    public MemberController(MemberService memberService)
    {
        this.memberService = memberService;
    }

    // 생성자 주입2: 생성자를 빼고 필드에다가 @Autowired를 넣어줌
    // 필드 주입은 별로 좋지 않다. 중간에 내가 바꿀수 있는 방법이 없
    // @Autowired private MemberService memberService;

    // 생성자 주입3: setter 를 통해서 주입됨
    // 단점: 누군가가 멤버컨트롤를 호출 했을때 public으로 열려 있어야한다.
    // 멤버서비스를 바꿀일이 없는데 노출 되어 문제가 된다.
    // 아무나 이 메소드를 호출할수 있기 때문에 좋은 방법이 아니다.
    // 개발할때 불필요한 호출은 없애야 한다.

    //private final MemberService memberService;
    //private MemberService memberService;
    //@Autowired
    //public void setMemberService(MemberService memberService){
    //this.memberService = memberService;
  //}

    // url enter, search 기능 할때 get을 쓴다.
    @GetMapping("/members/new")
    public String createForm() {
        return "members/createMemberForm";
    }

    // send data 직접 적으로 데이터를 처리함 post 방식
    @PostMapping("/members/new")
    public String create(MemberForm form) {
        Member member = new Member();
        member.setName(form.getName());
        memberService.join(member);

        return "redirect:/";
    }

}
