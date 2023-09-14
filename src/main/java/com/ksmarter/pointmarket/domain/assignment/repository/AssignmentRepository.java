package com.ksmarter.pointmarket.domain.assignment.repository;

import com.ksmarter.pointmarket.domain.assignment.domain.Assignment;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

public interface AssignmentRepository extends JpaRepository<Assignment, Long>, JpaSpecificationExecutor<Assignment> {
}