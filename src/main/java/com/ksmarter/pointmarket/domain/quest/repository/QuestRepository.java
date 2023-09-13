package com.ksmarter.pointmarket.domain.quest.repository;

import com.ksmarter.pointmarket.domain.quest.domain.Quest;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

public interface QuestRepository extends JpaRepository<Quest, Long>, JpaSpecificationExecutor<Quest> {
}