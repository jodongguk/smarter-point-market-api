package com.ksmarter.pointmarket.domain.budget.repository;

import com.ksmarter.pointmarket.domain.budget.domain.Budget;
import org.springframework.data.jpa.repository.JpaRepository;

public interface BudgetRepository extends JpaRepository<Budget, Long> {
}
