package uz.developers.paypal.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import uz.developers.paypal.entity.Card;

public interface CardRepository extends JpaRepository<Card,Integer> {



}
