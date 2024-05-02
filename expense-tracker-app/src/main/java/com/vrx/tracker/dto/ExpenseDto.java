package com.vrx.tracker.dto;

import com.vrx.tracker.entity.User;
import jakarta.persistence.Column;

import java.time.LocalDate;


public class ExpenseDto {
    private Long expenseId;

    private String description;

    private double amount;

    private LocalDate date;

    public UserDto getUser() {
        return user;
    }

    public void setUser(UserDto user) {
        this.user = user;
    }

    private UserDto user;

    public Long getExpenseId() {
        return expenseId;
    }

    public void setExpenseId(Long expenseId) {
        this.expenseId = expenseId;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public double getAmount() {
        return amount;
    }

    public void setAmount(double amount) {
        this.amount = amount;
    }

    public LocalDate getDate() {
        return date;
    }

    public void setDate(LocalDate date) {
        this.date = date;
    }

    @Override
    public String toString() {
        return "ExpenseDto{" +
                "expenseId=" + expenseId +
                ", description='" + description + '\'' +
                ", amount=" + amount +
                ", date=" + date +
                ", user=" + user +
                '}';
    }
}
