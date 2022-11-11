package uz.developers.paypal.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import uz.developers.paypal.entity.User;
import uz.developers.paypal.payload.ApiResponce;
import uz.developers.paypal.repository.UserRepository;
import uz.developers.paypal.service.UserService;

import java.util.List;

@Service
public class UserServiceImpl implements UserService {
    @Autowired
    UserRepository userRepository;


    @Override
    public List<User> getUsers() {
        return null;
    }

    @Override
    public User getUser(Integer id) {
        return null;
    }

    @Override
    public ApiResponce addUser(User user) {
        return null;
    }

    @Override
    public ApiResponce editUser(Integer id, User user) {
        return null;
    }

    @Override
    public ApiResponce deleteUser(Integer id) {
        return null;
    }
}
