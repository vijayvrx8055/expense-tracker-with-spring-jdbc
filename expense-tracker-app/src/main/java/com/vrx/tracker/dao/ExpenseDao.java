package com.vrx.tracker.dao;

import com.vrx.tracker.dto.ExpenseDto;

import java.util.List;

public interface ExpenseDao {
    ExpenseDto addExpense(String userId, ExpenseDto expenseDto);

    List<ExpenseDto> getAllExpensesByUsername(String userId);

    ExpenseDto getExpenseById(String userId, String expenseId);

    ExpenseDto updateExpense(String userId, String expenseId, ExpenseDto expenseDto);

    String deleteExpense(String userId, String expenseId);
}
