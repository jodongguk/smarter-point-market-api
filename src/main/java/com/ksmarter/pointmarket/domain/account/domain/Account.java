package com.ksmarter.pointmarket.domain.account.domain;

import jakarta.persistence.*;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.Comment;

import java.util.Set;

@Entity
@Table(name = "account")
@Comment("사용자 계정 정보")
@Getter
@NoArgsConstructor
public class Account {

    @Id
    @Column(name = "account_id")
    @Comment("사용자 고유번호")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Comment("사용자 아이디")
    @Column(name = "userid", length = 50, unique = true)
    private String userid;

    @Comment("사용자 비밀번호")
    @Column(name = "password", length = 100)
    private String password;

    @Column(name = "token_weight")
    private Long tokenWeight;

    @Comment("사용자 이름")
    @Column(name = "username", length = 50)
    private String username;

    @Comment("사용자 활성 유무")
    @Column(name = "activated")
    private boolean activated;

    @ManyToMany
    @JoinTable( // JoinTable은 테이블과 테이블 사이에 별도의 조인 테이블을 만들어 양 테이블간의 연관관계를 설정 하는 방법
            name = "account_authority",
            joinColumns = {@JoinColumn(name = "account_id", referencedColumnName = "account_id")},
            inverseJoinColumns = {@JoinColumn(name = "authority_name", referencedColumnName = "authority_name")})
    private Set<Authority> authorities;

    @Builder
    public Account(String userid, String password, String username, Set<Authority> authorities, boolean activated) {
        this.userid = userid;
        this.password = password;
        this.username = username;
        this.authorities = authorities;
        this.activated = activated;
        this.tokenWeight = 1L; // 초기 가중치는 1
    }

    public void increaseTokenWeight() {
        this.tokenWeight++;
    }
}
