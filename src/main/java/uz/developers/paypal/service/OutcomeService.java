package uz.developers.paypal.service;

import uz.developers.paypal.entity.Outcome;
import uz.developers.paypal.payload.ApiResponce;
import uz.developers.paypal.payload.OutcomeDto;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

public interface OutcomeService {
    List<Outcome> getOutcomes(HttpServletRequest request);

    Outcome getOutcome(Integer id,HttpServletRequest request);

    ApiResponce addOutcome(OutcomeDto outcomeDto,HttpServletRequest request);

    //ApiResponce editOutcome(Integer id, OutcomeDto outcomeDto);

    ApiResponce deleteOutcome(Integer id,HttpServletRequest request);
}
