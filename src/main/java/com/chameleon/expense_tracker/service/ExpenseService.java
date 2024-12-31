package com.chameleon.expense_tracker.service;

import com.chameleon.expense_tracker.entity.Expense;
import com.chameleon.expense_tracker.repository.ExpenseRepo;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Slf4j
@Service
public class ExpenseService {

    private final ExpenseRepo expenseRepo;

    @Autowired
    public ExpenseService(ExpenseRepo expenseRepo) {
        this.expenseRepo = expenseRepo;
    }

    public List<Expense> getAllExpenses() {
        return expenseRepo.findAll();
    }

    public Expense getExpenseById(long id) {
        Optional<Expense> optionalExpense = expenseRepo.findById(id);
        return optionalExpense.orElse(null);
    }

    public Expense saveExpense (Expense expense) {
        expense.setDateCreated(LocalDateTime.now());
        expense.setDateModified(LocalDateTime.now());

        return expenseRepo.save(expense);
    }

    public Expense updateExpense (Expense expense) {
        Optional<Expense> existingExpense = expenseRepo.findById(expense.getId());
        if (existingExpense.isPresent()) {
            expense.setDateCreated(existingExpense.get().getDateCreated());
            expense.setDateModified(LocalDateTime.now());

            return expenseRepo.save(expense);
        }
        return null;
    }

    public void deleteExpenseById (Long id) {
        expenseRepo.deleteById(id);
    }
}
