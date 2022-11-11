package uz.developers.paypal.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import uz.developers.paypal.entity.Income;
import uz.developers.paypal.payload.ApiResponce;
import uz.developers.paypal.repository.IncomeRepository;
import uz.developers.paypal.security.JwtProvider;
import uz.developers.paypal.service.IncomeService;

import javax.servlet.http.HttpServletRequest;
import java.util.List;
import java.util.Optional;

@Service
public class IncomeServiceImpl implements IncomeService {
    private final JwtProvider jwtProvider;
    private final IncomeRepository incomeRepository;

    @Autowired(required = false)
    public IncomeServiceImpl(JwtProvider jwtProvider, IncomeRepository incomeRepository) {
        this.jwtProvider = jwtProvider;
        this.incomeRepository = incomeRepository;
    }

    @Override
    public List<Income> getIncomes(HttpServletRequest request) {
        String token = request.getHeader("Authorization");
        token = token.substring(7);
        String username = jwtProvider.getUserNameFromToken(token);
        return incomeRepository.findByFromCard_UserName(username);
    }

    @Override
    public Income getIncome(Integer id, HttpServletRequest request) {
        String token = request.getHeader("Authorization");
        token = token.substring(7);
        String username = jwtProvider.getUserNameFromToken(token);
        Optional<Income> optionalIncome = incomeRepository.findById(id);
        if (!optionalIncome.isPresent()) {
            return new Income();
        }
        if (!username.equals(optionalIncome.get().getFromCard().getUsername())) {
            return new Income();
        }
        return optionalIncome.get();
    }


    @Override
    public ApiResponce deleteIncome(Integer id, HttpServletRequest request) {
        String token = request.getHeader("Authorization");
        token = token.substring(7);
        String username = jwtProvider.getUserNameFromToken(token);
        Optional<Income> byId = incomeRepository.findById(id);
        if (!byId.isPresent()) {
            return new ApiResponce("Id not found!", false);
        }
        if (!username.equals(byId.get().getFromCard().getUsername())) {
            return new ApiResponce("Username not found!", false);
        }
        incomeRepository.deleteById(id);
        return new ApiResponce("Delete success", true);
    }
}
