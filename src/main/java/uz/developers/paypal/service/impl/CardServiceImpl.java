package uz.developers.paypal.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import uz.developers.paypal.entity.Card;
import uz.developers.paypal.payload.ApiResponce;
import uz.developers.paypal.payload.CardDto;
import uz.developers.paypal.repository.CardRepository;
import uz.developers.paypal.security.JwtProvider;
import uz.developers.paypal.service.CardService;

import javax.servlet.http.HttpServletRequest;
import java.util.List;
import java.util.Optional;

@Service
public class CardServiceImpl implements CardService {

    @Autowired
    CardRepository cardRepository;
    @Autowired
    JwtProvider jwtProvider;

    @Override
    public List<Card> getCards(HttpServletRequest request) {
        String token = request.getHeader("Authorization");
        token = token.substring(7);
        String username = jwtProvider.getUserNameFromToken(token);
        return cardRepository.findAllByUserName(username);
    }

    @Override
    public Card getCard(Integer id, HttpServletRequest request) {
        String token = request.getHeader("Authorization");
        token = token.substring(7);
        String username = jwtProvider.getUserNameFromToken(token);
        Optional<Card> cardOptional = cardRepository.findById(id);
        if (!cardOptional.isPresent()) {
            return new Card();
        }
        if (!username.equals(cardOptional.get().getUsername())) {
            return new Card();
        }
        return cardOptional.get();
    }


    @Override
    public ApiResponce addCard(CardDto cardDto, HttpServletRequest request) {
        String token = request.getHeader("Authorization");
        token = token.substring(7);
        String username = jwtProvider.getUserNameFromToken(token);
        if(cardRepository.existsByCardNumber(cardDto.getCardNumber())) {
            return new ApiResponce("Already exist by cardNumber", false);
        }
        Card card = new Card();
        card.setCardNumber(cardDto.getCardNumber());
        card.setBalance(cardDto.getBalance());
        card.setUsername(username);
        card.setExpiredDate(cardDto.getExpiredDate());
        cardRepository.save(card);
        return new ApiResponce("Card is successfully added",true);
    }

    @Override
    public ApiResponce editCard(Integer id, CardDto cardDto, HttpServletRequest request) {
        String token = request.getHeader("Authorization");
        token = token.substring(7);
        String username = jwtProvider.getUserNameFromToken(token);
        Optional<Card> optionalCard = cardRepository.findById(id);
        if (optionalCard.isEmpty())
            return new ApiResponce("CardNumber is not found",false);
        if (!username.equals(optionalCard.get().getUsername()))
            return new ApiResponce("CardNumber is not found",false);
        Card card = optionalCard.get();
        if (card.isActive()){
            if (cardDto.getBalance() >0)
                card.setBalance(cardDto.getBalance());
            else {
                return new ApiResponce("Card balance is zero!", false);
            }
            if (cardDto.getExpiredDate() != null) {
                card.setExpiredDate(cardDto.getExpiredDate());
            }
        } else {
            return new ApiResponce("Card is blocked!", false);
        }
        cardRepository.save(card);
        return new ApiResponce("Card is edited!", true);
    }

    @Override
    public ApiResponce deleteCard(Integer id, HttpServletRequest request) {
        String token = request.getHeader("Authorization");
        token = token.substring(7);
        String username = jwtProvider.getUserNameFromToken(token);//username
        Optional<Card> byId = cardRepository.findById(id);
        if (!byId.isPresent()) return new ApiResponce("This id is not found",false);
        if (!username.equals(byId.get().getUsername())) return new ApiResponce(" Username is not found",false);
        cardRepository.deleteById(id);
        return new ApiResponce("Deleting successfully",false);
    }
}
