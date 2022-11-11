package uz.developers.paypal.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import uz.developers.paypal.entity.Card;

import java.util.List;

public interface CardRepository extends JpaRepository<Card,Integer> {


    boolean existsByCardNumber(String cardNumber);
    List<Card> findAllByUserName(String userName);





}
