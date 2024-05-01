package uz.developers.paypal.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import uz.developers.paypal.entity.Income;

import java.util.List;

public interface IncomeRepository  extends JpaRepository<Income,Integer> {

    List<Income> findByFromCard_UserName(String fromCard_userName);


}
