package uz.developers.paypal.service;

import uz.developers.paypal.entity.Outcome;
import uz.developers.paypal.payload.ApiResponce;
import uz.developers.paypal.payload.OutcomeDto;

import java.util.List;

public interface OutcomeService {
    List<Outcome> getOutcomes();

    Outcome getOutcome(Integer id);

    ApiResponce addOutcome(OutcomeDto outcomeDto);

    ApiResponce editOutcome(Integer id, OutcomeDto outcomeDto);

    ApiResponce deleteOutcome(Integer id);
}
