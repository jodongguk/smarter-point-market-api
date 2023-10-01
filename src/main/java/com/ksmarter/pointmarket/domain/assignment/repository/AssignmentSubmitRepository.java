package com.ksmarter.pointmarket.domain.assignment.repository;

import com.ksmarter.pointmarket.domain.assignment.domain.AssignmentSubmit;
import com.ksmarter.pointmarket.domain.common.enums.AssignmentSubmitTypes;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.lang.NonNull;
import org.springframework.transaction.annotation.Transactional;

public interface AssignmentSubmitRepository extends JpaRepository<AssignmentSubmit, Long>, JpaSpecificationExecutor<AssignmentSubmit> {
    @Transactional
    @Modifying
    @Query("update AssignmentSubmit a set a.assignmentSubmitType = ?1 where a.id = ?2")
    int updateAssignmentSubmitTypeById(@NonNull AssignmentSubmitTypes assignmentSubmitType, Long id);

}