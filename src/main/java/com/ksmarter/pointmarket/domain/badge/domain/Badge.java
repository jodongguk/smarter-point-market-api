package com.ksmarter.pointmarket.domain.badge.domain;

import com.ksmarter.pointmarket.domain.account.domain.Account;
import com.ksmarter.pointmarket.domain.common.domain.BaseEntity;
import jakarta.persistence.*;
import lombok.Getter;
import org.hibernate.annotations.Comment;

@Entity
@Table(name = "badge")
@Comment("배지 정보")
@Getter
public class Badge extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "badge_id", nullable = false)
    private Long id;

    @Comment("배지 제목")
    @Column(name = "title", nullable = false)
    private String title;

    @Comment("배지 설명")
    @Column(name = "description")
    @Lob
    private String description;

    @ManyToOne
    @JoinColumn(name = "")
    private Account sender;


    @ManyToOne
    @JoinColumn(name = "")
    private Account recipient;
}