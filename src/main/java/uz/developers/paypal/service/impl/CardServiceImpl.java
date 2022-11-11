package uz.developers.paypal.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import uz.developers.paypal.entity.Card;
import uz.developers.paypal.payload.ApiResponce;
import uz.developers.paypal.payload.CardDto;
import uz.developers.paypal.repository.CardRepository;
import uz.developers.paypal.service.CardService;

import java.util.List;

@Service
public class CardServiceImpl implements CardService {

    @Autowired
    CardRepository cardRepository;

    @Override
    public List<Card> getCards() {
        return null;
    }

    @Override
    public Card getCard(Integer id) {
        return null;
    }

    @Override
    public ApiResponce addCard(CardDto cardDto) {
        return null;
    }

    @Override
    public ApiResponce editCard(Integer id, CardDto cardDto) {
        return null;
    }

    @Override
    public ApiResponce deleteCard(Integer id) {
        return null;
    }
}
