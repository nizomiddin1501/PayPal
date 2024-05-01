package uz.developers.paypal.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import uz.developers.paypal.entity.Outcome;

import java.util.List;

public interface OutcomeRepository  extends JpaRepository<Outcome,Integer> {

    List<Outcome> findByFromCard_UserName(String fromCard_userName);

}
