package com.ksmarter.pointmarket.domain.budget.service;

import com.ksmarter.pointmarket.domain.budget.domain.Budget;
import com.ksmarter.pointmarket.domain.budget.repository.BudgetRepository;
import com.ksmarter.pointmarket.domain.institute.domain.Institute;
import com.ksmarter.pointmarket.domain.institute.repository.InstituteRepository;
import com.ksmarter.pointmarket.generated.types.InputBudget;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;

@Service
public class BudgetService {

    private final BudgetRepository budgetRepository;

    private final InstituteRepository instituteRepository;

    public BudgetService(BudgetRepository budgetRepository, InstituteRepository instituteRepository) {
        this.budgetRepository = budgetRepository;
        this.instituteRepository = instituteRepository;
    }


    public Budget saveByInputBudget(InputBudget inputBudget) {

        Budget.BudgetBuilder budgetBuilder = Budget.builder();

        Institute institute = instituteRepository.findById(Long.parseLong(inputBudget.getInstitute().getId())).orElseThrow();

        budgetBuilder.institute(institute);
        budgetBuilder.startDate(inputBudget.getStartDate());
        budgetBuilder.endDate(inputBudget.getEndDate());
        budgetBuilder.budgetAmount(BigDecimal.valueOf(inputBudget.getBudgetAmount()));

        return budgetRepository.save(budgetBuilder.build());
    }
}
