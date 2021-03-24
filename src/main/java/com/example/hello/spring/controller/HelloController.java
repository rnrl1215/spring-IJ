package com.example.hello.spring.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller

public class HelloController {

    // get method /hello 로 들어 올때 매칭 시킴
    @GetMapping("hello")
    public String hello(Model model){
        //key:data2 value:hello
        model.addAttribute("data2","hello");
        return "hello";
    }

    @GetMapping("hello-mvc")
    public String helloMVC(@RequestParam("name") String name, Model model){
        model.addAttribute("name",name);
        return "hello-template";
    }
}
