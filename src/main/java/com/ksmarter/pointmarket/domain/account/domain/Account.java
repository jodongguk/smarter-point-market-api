package com.ksmarter.pointmarket.domain.account.domain;

import com.ksmarter.pointmarket.domain.common.domain.BaseEntity;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.Comment;

import java.util.Set;

@Entity
@Comment("사용자 계정 정보")
@Table(name = "account")
@Getter
@NoArgsConstructor
public class Account extends BaseEntity {

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

    @OneToMany(mappedBy = "account")
    private Set<AccountAuthority> authorities;

    @OneToMany(mappedBy = "account")
    private Set<AccountChildren> childrens;

    @OneToMany(mappedBy = "account")
    private Set<AccountInstitute> institutes;

    @OneToMany(mappedBy = "account")
    private Set<AccountFranchisor> franchisors;
}
