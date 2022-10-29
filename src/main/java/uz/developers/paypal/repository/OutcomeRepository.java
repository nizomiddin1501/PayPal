package uz.developers.paypal.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import uz.developers.paypal.entity.Outcome;

public interface OutcomeRepository  extends JpaRepository<Outcome,Integer> {



}
