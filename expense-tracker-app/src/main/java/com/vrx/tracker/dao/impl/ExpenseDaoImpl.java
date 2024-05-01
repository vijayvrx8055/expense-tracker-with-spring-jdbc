package com.vrx.tracker.dao.impl;

import com.vrx.tracker.dao.ExpenseDao;
import com.vrx.tracker.dao.UserDao;
import com.vrx.tracker.dto.ExpenseDto;
import com.vrx.tracker.entity.Expense;
import com.vrx.tracker.entity.User;
import com.vrx.tracker.mapper.ExpenseMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Repository
public class ExpenseDaoImpl implements ExpenseDao {

    @Autowired
    private JdbcTemplate template;

    @Autowired
    private UserDao userDao;

    @Override
    public ExpenseDto addExpense(String userId, ExpenseDto expenseDto) {
        String sql = "INSERT INTO expenses(description, amount, date, user_id) VALUES (?, ?, ?, ?)";
        int row = template.update(sql, expenseDto.getDescription(), expenseDto.getAmount(), expenseDto.getDate(), expenseDto.getUserId());
        if (row > 0) {
            return expenseDto;
        } else {
            return null;
        }

    }

    @Override
    public List<ExpenseDto> getAllExpensesByUsername(String userId) {
        String sql = "SELECT u.user_id, u.username, e.expense_id, e.description, e.amount, e.date " +
                " FROM users u " +
                " LEFT JOIN expenses e ON u.user_id = e.user_id" +
                " WHERE u.user_id = ?";
        List<Expense> expenseList = template.query(sql, new Object[]{userId}, resultSet -> {
            List<Expense> expenses = new ArrayList<>();
            while (resultSet.next()) {
                Expense expense = new Expense();
                expense.setExpenseId(resultSet.getLong("expense_id"));
                expense.setDescription(resultSet.getString("description"));
                expense.setAmount(resultSet.getDouble("amount"));
                expense.setDate(resultSet.getObject("date", LocalDate.class));
                User user = userDao.getUserByUserId(resultSet.getString("user_id"));
                expense.setUser(user);
                System.out.println(expense);
                expenses.add(expense);
            }
            return expenses;
        });
        return expenseList.stream().map(ExpenseMapper.MAPPER::mapExpenseToExpenseDto).toList();
    }

    @Override
    public ExpenseDto getExpenseById(String userId, String expenseId) {
        return null;
    }

    @Override
    public ExpenseDto updateExpense(String userId, String expenseId, ExpenseDto expenseDto) {
        return null;
    }

    @Override
    public String deleteExpense(String userId, String expenseId) {
        return null;
    }
}
