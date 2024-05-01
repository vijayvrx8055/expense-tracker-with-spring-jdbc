package com.vrx.tracker.controller;

import com.vrx.tracker.dto.ExpenseDto;
import com.vrx.tracker.service.ExpenseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/expenses")
public class ExpenseController {

    @Autowired
    private ExpenseService expenseService;

    @PostMapping("/{userId}")
    public ResponseEntity<ExpenseDto> addExpense(@PathVariable String userId, @RequestBody ExpenseDto expenseDto) {
        ExpenseDto response = expenseService.addExpense(userId, expenseDto);
        return new ResponseEntity<>(response, HttpStatus.CREATED);
    }

    @GetMapping("/{userId}")
    public ResponseEntity<List<ExpenseDto>> getAllExpensesByUsername(@PathVariable String userId) {
        List<ExpenseDto> response = expenseService.getAllExpensesByUsername(userId);
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @GetMapping("/{userId}/{expenseId}")
    public ResponseEntity<ExpenseDto> getExpenseById(@PathVariable String userId, @PathVariable String expenseId) {
        ExpenseDto response = expenseService.getExpenseById(userId, expenseId);
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @PutMapping("/{userId}/{expenseId}")
    public ResponseEntity<ExpenseDto> updateExpense(@PathVariable String userId, @PathVariable String expenseId, @RequestBody ExpenseDto expenseDto) {
        ExpenseDto response = expenseService.updateExpense(userId, expenseId, expenseDto);
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @DeleteMapping("/{userId}/{expenseId}")
    public ResponseEntity<String> deleteExpense(@PathVariable String userId, @PathVariable String expenseId) {
        String response = expenseService.deleteExpense(userId, expenseId);
        return new ResponseEntity<>(response, HttpStatus.OK);
    }


}
