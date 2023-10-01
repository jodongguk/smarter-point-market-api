package com.ksmarter.pointmarket.domain.account.domain;

import com.ksmarter.pointmarket.domain.assignment.domain.Assignment;
import com.ksmarter.pointmarket.domain.common.domain.BaseEntity;
import com.ksmarter.pointmarket.domain.common.enums.AuthorityRoles;
import com.ksmarter.pointmarket.domain.credit.domain.Credit;
import com.ksmarter.pointmarket.domain.institute.domain.InstituteChildren;
import com.ksmarter.pointmarket.generated.types.AccountFilter;
import com.ksmarter.pointmarket.generated.types.AssignmentFilter;
import jakarta.persistence.*;
import jakarta.persistence.criteria.Predicate;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.apache.commons.lang3.StringUtils;
import org.hibernate.annotations.Comment;
import org.springframework.data.jpa.domain.Specification;

import java.time.LocalDate;
import java.util.HashSet;
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

    @Comment("사용자 이름")
    @Column(name = "name", length = 50)
    private String name;

    @Comment("사용자 연락처")
    @Column(name = "phone_number", length = 20)
    private String phoneNumber;

    @Comment("사용자 생년월일")
    @Column(name = "birth_date")
    private LocalDate birthDate;

    @Comment("사용자 토큰 가중")
    @Column(name = "token_weight")
    private Long tokenWeight;

    @Comment("사용자 활성 유무")
    @Column(name = "activated")
    private boolean activated;

    @OneToMany(mappedBy = "account", cascade = CascadeType.ALL)
    private Set<AccountAuthority> authorities = new HashSet<>();

    @OneToMany(mappedBy = "children", cascade = CascadeType.ALL)
    private Set<AccountChildren> childrens = new HashSet<>();

    @OneToMany(mappedBy = "account", cascade = CascadeType.ALL)
    private Set<AccountInstitute> institutes = new HashSet<>();

    @OneToMany(mappedBy = "account", cascade = CascadeType.ALL)
    private Set<AccountFranchisor> franchisors = new HashSet<>();

    @OneToMany(mappedBy = "children", cascade = CascadeType.ALL)
    private Set<InstituteChildren> attendInstitutes = new HashSet<>();

    @OneToOne(mappedBy = "account", cascade = CascadeType.ALL)
    private Credit credit = new Credit();

    @Builder
    public Account(String userid, String password, String name, String phoneNumber, LocalDate birthDate, boolean activated, Set<AccountAuthority> authorities, Set<AccountChildren> childrens, Set<AccountInstitute> institutes, Set<AccountFranchisor> franchisors, Set<InstituteChildren> attendInstitutes, Credit credit) {
        this.userid = userid;
        this.password = password;
        this.name = name;
        this.phoneNumber = phoneNumber;
        this.birthDate = birthDate;
        this.authorities = authorities;
        this.childrens = childrens;
        this.institutes = institutes;
        this.franchisors = franchisors;
        this.attendInstitutes = attendInstitutes;
        this.credit = credit;
        this.activated = activated;
    }

    public void addAuthorities(Set<AccountAuthority> authorities) {
        this.authorities = authorities;
        authorities.forEach(accountAuthority -> accountAuthority.setAccount(this));
    }

    public void addInstitutes(Set<AccountInstitute> institutes) {
        this.institutes = institutes;
        institutes.forEach(accountInstitute -> accountInstitute.setAccount(this));
    }

    public void addChildrens(Set<AccountChildren> childrens) {
        this.childrens = childrens;
        childrens.forEach(accountChildren -> accountChildren.setParent(this));
    }

    public static Specification<Account> inputFilterToSpec(AccountFilter filter) {
        return (root, query, builder) -> {
            Predicate p = builder.conjunction();

            if(StringUtils.isNotEmpty(filter.getName())) {
                p = builder.and(p, builder.like(root.get("name"), "%" + filter.getName() + "%"));
            }

            if(filter.getBirthDate() != null) {
                p = builder.and(p, builder.equal(root.get("birthDate"), filter.getBirthDate()));
            }

            if(StringUtils.isNotEmpty(filter.getRole())) {
                p = builder.and(p, builder.equal(root.get("authorities").get("authority").get("authorityName"), AuthorityRoles.of(filter.getRole()).getValue()));
            }

            return p;
        };
    }
}
