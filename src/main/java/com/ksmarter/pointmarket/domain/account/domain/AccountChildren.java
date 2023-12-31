package com.ksmarter.pointmarket.domain.account.domain;

import com.ksmarter.pointmarket.domain.common.domain.BaseEntity;
import com.ksmarter.pointmarket.domain.institute.domain.Institute;
import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.Comment;

import java.io.Serializable;

@Entity
@Comment("사용자 자녀 정보")
@Table(name = "account_children")
@Getter
@NoArgsConstructor
public class AccountChildren extends BaseEntity {

    @EmbeddedId
    private AccountChildrenId id = new AccountChildrenId();

    @Setter
    @ManyToOne
    @JoinColumn(name = "parent_id")
    @MapsId("parentId")
    private Account parent;

    @ManyToOne
    @JoinColumn(name = "children_id")
    @MapsId("childrenId")
    private Account children;

    @Builder
    public AccountChildren(Account parent, Account children) {
        this.parent = parent;
        this.children = children;
    }

    @Embeddable
    @Getter
    @NoArgsConstructor
    @AllArgsConstructor
    @EqualsAndHashCode
    public static class AccountChildrenId implements Serializable {
        private Long parentId;
        private Long childrenId;
    }
}
