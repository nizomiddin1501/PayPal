package uz.developers.paypal.service;

import uz.developers.paypal.entity.Card;
import uz.developers.paypal.payload.ApiResponce;
import uz.developers.paypal.payload.CardDto;

import java.util.List;

public interface CardService {
    List<Card> getCards();

    Card getCard(Integer id);

    ApiResponce addCard(CardDto cardDto);

    ApiResponce editCard(Integer id, CardDto cardDto);

    ApiResponce deleteCard(Integer id);
}
