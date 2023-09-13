package com.ksmarter.pointmarket.domain.account.domain;

import com.ksmarter.pointmarket.domain.common.domain.BaseEntity;
import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.Comment;

import java.io.Serializable;

@Entity
@Comment("사용자 권한 정보")
@Table(name = "account_authority")
@Getter
@NoArgsConstructor
public class AccountAuthority extends BaseEntity {

    @EmbeddedId
    private AccountAuthorityId id = new AccountAuthorityId();

    @ManyToOne
    @JoinColumn(name = "account_id")
    @MapsId("accountId")
    private Account account;

    @ManyToOne
    @JoinColumn(name = "authority_name")
    @MapsId("authorityName")
    private Authority authority;

    @Embeddable
    @Getter
    @NoArgsConstructor
    @AllArgsConstructor
    @EqualsAndHashCode
    public static class AccountAuthorityId implements Serializable {
        private Long accountId;
        private String authorityName;
    }
}
