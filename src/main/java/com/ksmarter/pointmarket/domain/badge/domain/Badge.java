package com.ksmarter.pointmarket.domain.badge.domain;

import com.ksmarter.pointmarket.domain.account.domain.Account;
import com.ksmarter.pointmarket.domain.common.domain.BaseEntity;
import com.ksmarter.pointmarket.domain.institute.domain.Institute;
import jakarta.persistence.*;
import lombok.Getter;
import org.hibernate.annotations.Comment;

@Entity
@Table(name = "badge")
@Comment("배지 정보")
@Getter
public class Badge extends BaseEntity {

    @Comment("배지 고유키")
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

    @Comment("배지 수여 학원")
    @ManyToOne
    @JoinColumn(name = "institute_id", nullable = true)
    private Institute institute;

    @Comment("배지 수여자")
    @ManyToOne
    @JoinColumn(name = "sender")
    private Account sender;

    @Comment("배지 취득자")
    @ManyToOne
    @JoinColumn(name = "recipient")
    private Account recipient;
}