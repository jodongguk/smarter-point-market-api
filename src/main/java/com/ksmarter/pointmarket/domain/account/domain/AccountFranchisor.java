package com.ksmarter.pointmarket.domain.account.domain;

import com.ksmarter.pointmarket.domain.common.domain.BaseEntity;
import com.ksmarter.pointmarket.domain.franchisor.domain.Franchisor;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.Comment;

import java.io.Serializable;

@Entity
@Comment("사용자 가맹점 정보")
@Table(name = "account_franchisor")
@Getter
@NoArgsConstructor
public class AccountFranchisor extends BaseEntity {

    @EmbeddedId
    private AccountFranchisorId id = new AccountFranchisorId();

    @ManyToOne
    @JoinColumn(name = "account_id")
    @MapsId("accountId")
    private Account account;

    @ManyToOne
    @JoinColumn(name = "franchisor_id")
    @MapsId("franchisorId")
    private Franchisor franchisor;


    @Embeddable
    @Getter
    @NoArgsConstructor
    @AllArgsConstructor
    @EqualsAndHashCode
    public static class AccountFranchisorId implements Serializable {
        private Long accountId;
        private Long franchisorId;
    }
}
