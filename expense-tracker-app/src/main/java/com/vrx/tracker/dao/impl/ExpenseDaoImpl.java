package com.vrx.tracker.dao.impl;

import com.vrx.tracker.dao.ExpenseDao;
import com.vrx.tracker.dao.UserDao;
import com.vrx.tracker.dto.ExpenseDto;
import com.vrx.tracker.dto.UserDto;
import com.vrx.tracker.entity.Expense;
import com.vrx.tracker.entity.User;
import com.vrx.tracker.mapper.ExpenseMapper;
import com.vrx.tracker.mapper.UserMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@Repository
public class ExpenseDaoImpl implements ExpenseDao {

    @Autowired
    private JdbcTemplate template;

    @Autowired
    private UserDao userDao;

    @Override
    public ExpenseDto addExpense(String userId, ExpenseDto expenseDto) {
        String sql = "INSERT INTO expenses(description, amount, date, user_id) VALUES (?, ?, ?, ?)";
        int row = template.update(sql, expenseDto.getDescription(), expenseDto.getAmount(), expenseDto.getDate(), userId);
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
        List<ExpenseDto> expenseList = template.query(sql, new Object[]{userId}, resultSet -> {
            List<ExpenseDto> expenses = new ArrayList<>();
            while (resultSet.next()) {
                ExpenseDto expense = new ExpenseDto();
                expense.setExpenseId(resultSet.getLong("expense_id"));
                expense.setDescription(resultSet.getString("description"));
                expense.setAmount(resultSet.getDouble("amount"));
                expense.setDate(resultSet.getObject("date", LocalDate.class));
                User user = userDao.getUserByUserId(resultSet.getString("user_id"));
                UserDto userDto = UserMapper.MAPPER.mapUserToUserDto(user);
                expense.setUser(userDto);
                System.out.println(expense);
                expenses.add(expense);
            }
            return expenses;
        });
        return expenseList;
    }

    @Override
    public ExpenseDto getExpenseById(String userId, String expenseId) {
        String sql = "Select * from expenses where user_id=? and expense_id=?";
        return template.queryForObject(sql, new Object[]{userId, expenseId}, (rs, rowNum) -> {
            ExpenseDto expenseDto = new ExpenseDto();
            expenseDto.setExpenseId(rs.getLong("expense_id"));
            expenseDto.setAmount(rs.getDouble("amount"));
            expenseDto.setDate(rs.getDate("date").toLocalDate());
            expenseDto.setDescription(rs.getString("description"));
            expenseDto.setUser(UserMapper.MAPPER.mapUserToUserDto(userDao.getUserByUserId(userId)));
            return expenseDto;
        });
    }

    @Override
    public ExpenseDto updateExpense(String userId, String expenseId, ExpenseDto expenseDto) {
        String sql = "UPDATE expenses SET description=?, amount=?, date=? WHERE user_id=? AND expense_id=?";
        int update = template.update(sql, expenseDto.getDescription(), expenseDto.getAmount(), expenseDto.getDate(), userId, expenseId);
        if (update > 0) {
            return expenseDto;
        }
        return null;
    }

    @Override
    public String deleteExpense(String userId, String expenseId) {
        String sql = "DELETE FROM expenses WHERE user_id=? and expense_id=?";
        int update = template.update(sql, userId, expenseId);
        if (update > 0) {
            return "Expense Deleted Successfully!!";
        }
        return null;
    }
}
