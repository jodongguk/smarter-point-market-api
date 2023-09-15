package com.ksmarter.pointmarket.domain.credit.domain;

import com.ksmarter.pointmarket.domain.account.domain.Account;
import com.ksmarter.pointmarket.domain.common.domain.BaseEntity;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.Comment;

import java.math.BigDecimal;
import java.util.Set;

@Entity
@Comment("사용자 포인트 정보")
@Table(name = "credit", indexes = {
        @Index(name = "idx__account_id", columnList = "account_id")
})
@Getter
@NoArgsConstructor
public class Credit extends BaseEntity {

    @Id
    @Comment("사용자 포인트 고유번호")
    @Column(name = "credit_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @OneToOne
    @JoinColumn(name = "account_id", unique = true)
    private Account account;

    @Column(name = "balance")
    @Comment("잔액")
    private BigDecimal balance = BigDecimal.ZERO;

    @OneToMany(mappedBy = "credit")
    private Set<CreditDetail> creditDetails;
}