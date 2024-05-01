package uz.developers.paypal.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import uz.developers.paypal.entity.Card;
import uz.developers.paypal.entity.Income;
import uz.developers.paypal.entity.Outcome;
import uz.developers.paypal.payload.ApiResponce;
import uz.developers.paypal.payload.OutcomeDto;
import uz.developers.paypal.repository.CardRepository;
import uz.developers.paypal.repository.IncomeRepository;
import uz.developers.paypal.repository.OutcomeRepository;
import uz.developers.paypal.security.JwtProvider;
import uz.developers.paypal.service.OutcomeService;

import javax.servlet.http.HttpServletRequest;
import java.util.Date;
import java.util.List;
import java.util.Optional;

@Service
public class OutcomeServiceImpl implements OutcomeService {
    private final OutcomeRepository outcomeRepository;
    private final JwtProvider jwtProvider;
    private final CardRepository cardRepository;
    private final IncomeRepository incomeRepository;

    @Autowired(required = false)
    public OutcomeServiceImpl(OutcomeRepository outcomeRepository, JwtProvider jwtProvider, CardRepository cardRepository, IncomeRepository incomeRepository) {
        this.outcomeRepository = outcomeRepository;
        this.jwtProvider = jwtProvider;
        this.cardRepository = cardRepository;
        this.incomeRepository = incomeRepository;
    }

    @Override
    public List<Outcome> getOutcomes(HttpServletRequest request) {
        String token = request.getHeader("Authorization");
        token = token.substring(7);
        String username = jwtProvider.getUserNameFromToken(token);
        return outcomeRepository.findByFromCard_UserName(username);
    }

    @Override
    public Outcome getOutcome(Integer id, HttpServletRequest request) {
        String token = request.getHeader("Authorization");
        token = token.substring(7);
        String username = jwtProvider.getUserNameFromToken(token);
        Optional<Outcome> outcomeOptional = outcomeRepository.findById(id);
        if (!outcomeOptional.isPresent()) {
            return new Outcome();
        }
        if (!username.equals(outcomeOptional.get().getFromCard().getUsername())) {
            return new Outcome();
        }
        return outcomeOptional.get();
    }

    @Override
    public ApiResponce addOutcome(OutcomeDto outcomeDto, HttpServletRequest request) {
        String token = request.getHeader("Authorization");

        token = token.substring(7);

        String username = jwtProvider.getUserNameFromToken(token);
        Optional<Card> optionalFromCard = cardRepository.findById(outcomeDto.getFromCardId());
        if (!optionalFromCard.isPresent()) return new ApiResponce("from Card not found", false);
        Optional<Card> toCardOpti = cardRepository.findById(outcomeDto.getToCardId());
        if (!toCardOpti.isPresent()) return new ApiResponce("to Card not found", false);
        if (outcomeDto.getAmount() < 0) return new ApiResponce("the value must be greater than zero", false);
        if (!username.equals(optionalFromCard.get().getUsername()))
            return new ApiResponce(" This is not your Card !", false);
        if (outcomeDto.getAmount() < 1000)
            return new ApiResponce("It is not possible to send less than 1000 funds", false);
        if (outcomeDto.getAmount() > (optionalFromCard.get().getBalance() - outcomeDto.getAmount() * outcomeDto.getCommission()))
            return new ApiResponce("there is not enough money in your account", false);
        if (optionalFromCard.get().getCardNumber().equals(toCardOpti.get().getCardNumber()))
            return new ApiResponce("Choose another card to send money because the sender's card number is the same", false);
        if (optionalFromCard.get().isActive() && toCardOpti.get().isActive()) {
            Outcome outcome = new Outcome();
            outcome.setAmount(outcomeDto.getAmount());
            outcome.setCommissionAmount(outcomeDto.getCommission());
            outcome.setFromCard(optionalFromCard.get());
            outcome.setToCard(toCardOpti.get());
            outcome.setDate(new java.sql.Date(new Date(System.currentTimeMillis()).getTime()));

            outcomeRepository.save(outcome);
            Income income = new Income();

            income.setAmount(outcomeDto.getAmount());
            income.setFromCard(optionalFromCard.get());
            income.setToCard(toCardOpti.get());
            income.setDate((new java.sql.Date(new Date(System.currentTimeMillis()).getTime())));

            incomeRepository.save(income);
            Card fromCard = optionalFromCard.get();
            Card toCard = toCardOpti.get();
            fromCard.setBalance(fromCard.getBalance() - (outcomeDto.getAmount() + outcomeDto.getAmount() * outcomeDto.getCommission()));
            cardRepository.save(fromCard);
            toCard.setBalance(toCard.getBalance() + outcomeDto.getAmount());
            cardRepository.save(toCard);
            return new ApiResponce("Successfully add", true);
        }
        return new ApiResponce("one of the card is blocked",false);
    }

    @Override
    public ApiResponce deleteOutcome(Integer id, HttpServletRequest request) {
        String token = request.getHeader("Authorization");
        token = token.substring(7);
        String username = jwtProvider.getUserNameFromToken(token);
        Optional<Outcome> byId = outcomeRepository.findById(id);
        if (!byId.isPresent()) return new ApiResponce("Id not found!",false);
        if (!username.equals(byId.get().getFromCard().getUsername()))
            return new ApiResponce("Username not found!",false);
        outcomeRepository.deleteById(id);
        return new ApiResponce("Delete success",true);
    }
}
