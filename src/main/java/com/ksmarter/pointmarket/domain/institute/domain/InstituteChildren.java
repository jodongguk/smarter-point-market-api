package com.ksmarter.pointmarket.domain.institute.domain;

import com.ksmarter.pointmarket.domain.account.domain.Account;
import com.ksmarter.pointmarket.domain.common.domain.BaseEntity;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.Comment;

import java.io.Serializable;

@Entity
@Comment("학원 등록 자녀 정보")
@Table(name = "institute_children")
@Getter
@NoArgsConstructor
public class InstituteChildren extends BaseEntity {

    @EmbeddedId
    private InstituteChildrenId id = new InstituteChildrenId();

    @ManyToOne
    @JoinColumn(name = "institute_id")
    @MapsId("instituteId")
    private Institute institute;

    @ManyToOne
    @JoinColumn(name = "account_id")
    @MapsId("accountId")
    private Account account;

    @Embeddable
    @Getter
    @NoArgsConstructor
    @AllArgsConstructor
    @EqualsAndHashCode
    public static class InstituteChildrenId implements Serializable {
        private Long instituteId;
        private Long childrenId;
    }
}
