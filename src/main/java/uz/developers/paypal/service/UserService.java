package uz.developers.paypal.service;

import uz.developers.paypal.entity.User;
import uz.developers.paypal.payload.ApiResponce;

import java.util.List;

public interface UserService {
    List<User> getUsers();

    User getUser(Integer id);

    ApiResponce addUser(User user);

    ApiResponce editUser(Integer id, User user);

    ApiResponce deleteUser(Integer id);


}
