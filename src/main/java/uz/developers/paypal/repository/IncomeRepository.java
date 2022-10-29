package uz.developers.paypal.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import uz.developers.paypal.entity.Income;

public interface IncomeRepository  extends JpaRepository<Income,Integer> {



}
