package com.ksmarter.pointmarket.domain.assignment.repository;

import com.ksmarter.pointmarket.domain.assignment.domain.AssignmentSubmit;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

public interface AssignmentSubmitRepository extends JpaRepository<AssignmentSubmit, Long>, JpaSpecificationExecutor<AssignmentSubmit> {

}