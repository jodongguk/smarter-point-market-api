package com.ksmarter.pointmarket.domain.account.domain;

import com.ksmarter.pointmarket.domain.common.domain.BaseEntity;
import com.ksmarter.pointmarket.domain.franchisor.domain.Franchisor;
import com.ksmarter.pointmarket.domain.institute.domain.Institute;
import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.Comment;

import java.io.Serializable;

@Entity
@Comment("사용자 학원 정보")
@Table(name = "account_institute")
@Getter
@NoArgsConstructor
public class AccountInstitute extends BaseEntity {

    @EmbeddedId
    private AccountInstituteId id = new AccountInstituteId();

    @Setter
    @ManyToOne
    @JoinColumn(name = "account_id")
    @MapsId("accountId")
    private Account account;

    @ManyToOne
    @JoinColumn(name = "institute_id")
    @MapsId("instituteId")
    private Institute institute;

    @Builder
    public AccountInstitute(Account account, Institute institute) {
        this.account = account;
        this.institute = institute;
    }

    @Embeddable
    @Getter
    @NoArgsConstructor
    @AllArgsConstructor
    @EqualsAndHashCode
    public static class AccountInstituteId implements Serializable {
        private Long instituteId;
        private Long accountId;
    }
}
