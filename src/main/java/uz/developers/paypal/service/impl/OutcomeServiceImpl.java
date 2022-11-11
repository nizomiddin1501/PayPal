package uz.developers.paypal.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import uz.developers.paypal.entity.Outcome;
import uz.developers.paypal.payload.ApiResponce;
import uz.developers.paypal.payload.OutcomeDto;
import uz.developers.paypal.repository.OutcomeRepository;
import uz.developers.paypal.service.OutcomeService;

import java.util.List;

@Service
public class OutcomeServiceImpl implements OutcomeService {
    @Autowired
    OutcomeRepository outcomeRepository;

    @Override
    public List<Outcome> getOutcomes() {
        return null;
    }

    @Override
    public Outcome getOutcome(Integer id) {
        return null;
    }

    @Override
    public ApiResponce addOutcome(OutcomeDto outcomeDto) {
        return null;
    }

    @Override
    public ApiResponce editOutcome(Integer id, OutcomeDto outcomeDto) {
        return null;
    }

    @Override
    public ApiResponce deleteOutcome(Integer id) {
        return null;
    }
}
