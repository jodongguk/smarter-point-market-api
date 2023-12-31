package com.ksmarter.pointmarket.domain.franchisor.domain;

import com.ksmarter.pointmarket.domain.account.domain.Account;
import com.ksmarter.pointmarket.domain.account.domain.AccountFranchisor;
import com.ksmarter.pointmarket.domain.account.domain.AccountInstitute;
import com.ksmarter.pointmarket.domain.common.domain.BaseEntity;
import jakarta.persistence.*;
import lombok.Getter;
import org.hibernate.annotations.Comment;

import java.util.Set;

@Entity
@Table(name = "franchisor")
@Comment("가맹점 정보")
@Getter
public class Franchisor extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "franchisor_id", nullable = false)
    private Long id;

    @Comment("학원명")
    @Column(name = "name", length = 20)
    private String name;

    @Comment("대표자명")
    @Column(name = "owner", length = 20)
    private String owner;

    @Comment("연락처")
    @Column(name = "tel", length = 20)
    private String tel;

    @Comment("주소1")
    @Column(name = "addr1", length = 200)
    private String addr1;

    @Comment("주소2")
    @Column(name = "addr2", length = 200)
    private String addr2;

    @Comment("우편번호")
    @Column(name = "zip", length = 20)
    private String zip;

    @Comment("사업자등록 번호")
    @Column(name = "business_registration_number", length = 20)
    private String businessRegistrationNumber;

    @OneToMany(mappedBy = "franchisor")
    private Set<AccountFranchisor> accounts;
}