package com.vrx.tracker.service.impl;

import com.vrx.tracker.dao.ExpenseDao;
import com.vrx.tracker.dto.ExpenseDto;
import com.vrx.tracker.service.ExpenseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ExpenseServiceImpl implements ExpenseService {

    @Autowired
    private ExpenseDao expenseDao;

    @Override
    public ExpenseDto addExpense(String userId, ExpenseDto expenseDto) {
        return expenseDao.addExpense(userId, expenseDto);
    }

    @Override
    public List<ExpenseDto> getAllExpensesByUsername(String userId) {
        return expenseDao.getAllExpensesByUsername(userId);
    }

    @Override
    public ExpenseDto getExpenseById(String userId, String expenseId) {
        return expenseDao.getExpenseById(userId, expenseId);
    }

    @Override
    public ExpenseDto updateExpense(String userId, String expenseId, ExpenseDto expenseDto) {
        return expenseDao.updateExpense(userId, expenseId, expenseDto);
    }

    @Override
    public String deleteExpense(String userId, String expenseId) {
        return expenseDao.deleteExpense(userId, expenseId);
    }
}
