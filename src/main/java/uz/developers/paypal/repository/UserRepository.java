package uz.developers.paypal.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import uz.developers.paypal.entity.User;

public interface UserRepository  extends JpaRepository<User,Integer> {



}
