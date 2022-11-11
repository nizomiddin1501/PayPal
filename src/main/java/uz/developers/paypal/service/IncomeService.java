package uz.developers.paypal.service;

import uz.developers.paypal.entity.Income;
import uz.developers.paypal.payload.ApiResponce;
import uz.developers.paypal.payload.IncomeDto;

import java.util.List;

public interface IncomeService {
    List<Income> getIncomes();

    Income getIncome(Integer id);

    ApiResponce addIncome(IncomeDto incomeDto);

    ApiResponce editIncome(Integer id, IncomeDto incomeDto);

    ApiResponce deleteIncome(Integer id);
}
