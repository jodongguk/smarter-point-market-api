package com.ksmarter.pointmarket.domain.account.domain;

import jakarta.persistence.*;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.Comment;

import java.util.Set;

@Entity
@Comment("사용자 계정 정보")
@Table(name = "account")
@Getter
@NoArgsConstructor
public class Account {

    @Id
    @Comment("사용자 고유번호")
    @Column(name = "account_id")
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
    @Column(name = "name", length = 50)
    private String name;

    @Comment("사용자 활성 유무")
    @Column(name = "activated")
    private boolean activated;

    @ManyToMany
    @Comment("사용자 권한 맵핑")
    @JoinTable(name = "account_authority",
            joinColumns = {@JoinColumn(name = "account_id", referencedColumnName = "account_id")},
            inverseJoinColumns = {@JoinColumn(name = "authority_name", referencedColumnName = "authority_name")})
    private Set<Authority> authorities;

    @ManyToMany
    @Comment("사용자 자녀")
    @JoinTable(name = "account_child",
            joinColumns = {@JoinColumn(name = "account_id", referencedColumnName = "account_id")})
    private Set<Account> children;

    @Builder
    public Account(String userid, String password, String name, Set<Authority> authorities, boolean activated) {
        this.userid = userid;
        this.password = password;
        this.name = name;
        this.authorities = authorities;
        this.activated = activated;
        this.tokenWeight = 1L; // 초기 가중치는 1
    }

    public void increaseTokenWeight() {
        this.tokenWeight++;
    }
}
