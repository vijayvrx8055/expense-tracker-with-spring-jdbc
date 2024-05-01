package com.vrx.tracker.mapper;

import com.vrx.tracker.dto.ExpenseDto;
import com.vrx.tracker.entity.Expense;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper
public interface ExpenseMapper {

    ExpenseMapper MAPPER = Mappers.getMapper(ExpenseMapper.class);

    ExpenseDto mapExpenseToExpenseDto(Expense expense);

    Expense mapExpenseDtoToExpense(ExpenseDto expenseDto);
}
