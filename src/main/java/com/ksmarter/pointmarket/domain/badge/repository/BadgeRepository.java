package com.ksmarter.pointmarket.domain.badge.repository;

import com.ksmarter.pointmarket.domain.badge.domain.Badge;
import org.springframework.data.jpa.repository.JpaRepository;

public interface BadgeRepository extends JpaRepository<Badge, Long> {
}
