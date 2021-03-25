package com.example.hello.spring.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class HelloController {

    // get method /hello 로 들어 올때 매칭 시킴
    @GetMapping("hello")
    public String hello(Model model){
        //key:data2 value:hello
        model.addAttribute("data2","hello");
        return "" + "hello";
    }

    @GetMapping("hello-mvc")
    public String helloMVC(@RequestParam("name") String name, Model model){
        model.addAttribute("name",name);
        return "hello-templ ate";
    }

    @GetMapping("hello-string")
    @ResponseBody //http에서 head부와 body부가 있는데 데이터를 직접 넣어 주겠다는거
    public String helloString(@RequestParam("name") String name){
        return "hello"+name;
    }

    @GetMapping("hello-api")
    @ResponseBody
    public Hello helloApi(@RequestParam("name") String name)
    {
        Hello hello = new Hello();
        hello.setName(name);
        return hello;
    }

    static class Hello {
        private String name;

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }
    }
}
