package com.chameleon.expense_tracker.controller;

import com.chameleon.expense_tracker.service.*;
import com.chameleon.expense_tracker.entity.*;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/expense/v1")
public class ExpenseController {

    private final ExpenseService expenseService;

    @Autowired
    public ExpenseController(ExpenseService expenseService) {
        this.expenseService = expenseService;
    }

    /**
     * This method is called when a GET request is made
     * URL: localhost:8080/expense/v1/
     * Purpose: Fetches all the expenses in the expense table
     * @return List of Expenses
     */
    @GetMapping("/")
    public ResponseEntity<List<Expense>> getAllExpenses(){
        return ResponseEntity.ok().body(expenseService.getAllExpenses());
    }

    /**
     * This method is called when a GET request is made
     * URL: localhost:8080/expense/v1/1 (or any other id)
     * Purpose: Fetches expense with the given id
     * @param id - expense id
     * @return Expense with the given id
     */
    @GetMapping("/{id}")
    public ResponseEntity<Expense> getExpenseById(@PathVariable Integer id)
    {
        return ResponseEntity.ok().body(expenseService.getExpenseById(id));
    }

    /**
     * This method is called when a POST request is made
     * URL: localhost:8080/expense/v1/
     * Purpose: Save an Expense entity
     * @param expense - Request body is an Expense entity
     * @return Saved Expense entity
     */
    @PostMapping("/")
    public ResponseEntity<Expense> saveExpense(@RequestBody Expense expense)
    {
        return ResponseEntity.ok().body(expenseService.saveExpense(expense));
    }

    /**
     * This method is called when a PUT request is made
     * URL: localhost:8080/expense/v1/
     * Purpose: Update an Expense entity
     * @param expense - Expense entity to be updated
     * @return Updated Expense
     */
    @PutMapping("/")
    public ResponseEntity<Expense> updateExpense(@RequestBody Expense expense)
    {
        return ResponseEntity.ok().body(expenseService.updateExpense(expense));
    }

    /**
     * This method is called when a PUT request is made
     * URL: localhost:8080/expense/v1/1 (or any other id)
     * Purpose: Delete an Expense entity
     * @param id - expense's id to be deleted
     * @return a String message indicating expense record has been deleted successfully
     */
    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteExpenseById(@PathVariable Long id)
    {
        expenseService.deleteExpenseById(id);
        return ResponseEntity.ok().body("Deleted expense successfully");
    }
}
