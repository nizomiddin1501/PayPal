package uz.developers.paypal.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import uz.developers.paypal.entity.Income;
import uz.developers.paypal.payload.ApiResponce;
import uz.developers.paypal.payload.IncomeDto;
import uz.developers.paypal.repository.IncomeRepository;
import uz.developers.paypal.service.IncomeService;

import java.util.List;

@Service
public class IncomeServiceImpl implements IncomeService {
    @Autowired
    IncomeRepository incomeRepository;

    @Override
    public List<Income> getIncomes() {
        return null;
    }

    @Override
    public Income getIncome(Integer id) {
        return null;
    }

    @Override
    public ApiResponce addIncome(IncomeDto incomeDto) {
        return null;
    }

    @Override
    public ApiResponce editIncome(Integer id, IncomeDto incomeDto) {
        return null;
    }

    @Override
    public ApiResponce deleteIncome(Integer id) {
        return null;
    }
}
