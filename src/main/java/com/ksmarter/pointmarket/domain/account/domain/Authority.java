package com.ksmarter.pointmarket.domain.account.domain;

import com.ksmarter.pointmarket.domain.common.domain.BaseEntity;
import jakarta.persistence.*;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.Comment;

import java.io.Serializable;
import java.util.Set;

@Entity
@Table(name = "authority")
@Comment("사용자 권한")
@Getter
@NoArgsConstructor
public class Authority extends BaseEntity implements Serializable {

    @Id
    @Column(name = "authority_name", length = 50)
    private String authorityName;

    @OneToMany(mappedBy = "authority")
    private Set<AccountAuthority> accountAuthorities;

    @Builder
    public Authority(String authorityName) {
        this.authorityName = authorityName;
    }
}
