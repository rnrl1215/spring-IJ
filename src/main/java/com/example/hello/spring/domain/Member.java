package com.example.hello.spring.domain;


//JPA를 사용하려면 엔티티를 등록해야한다.
//ORM 관계형 데이터 베이스

import javax.persistence.*;

@Entity
public class Member {

    //PK 등록 JPA 이용
    //DB가 알아서 채번 해주는걸 indentity 라고함.

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    // 컬럼네임. JPA이용
    @Column(name = "name")
    private String name;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
