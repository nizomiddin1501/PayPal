package uz.developers.paypal.service;

import uz.developers.paypal.entity.Card;
import uz.developers.paypal.payload.ApiResponce;
import uz.developers.paypal.payload.CardDto;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

public interface CardService {
    List<Card> getCards(HttpServletRequest request);

    Card getCard(Integer id,HttpServletRequest request);

    ApiResponce addCard(CardDto cardDto,HttpServletRequest request);

    ApiResponce editCard(Integer id, CardDto cardDto,HttpServletRequest request);

    ApiResponce deleteCard(Integer id,HttpServletRequest request);
}
