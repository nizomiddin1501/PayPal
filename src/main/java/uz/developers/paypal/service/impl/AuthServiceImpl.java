package uz.developers.paypal.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import uz.developers.paypal.entity.User;
import uz.developers.paypal.payload.ApiResponce;
import uz.developers.paypal.repository.UserRepository;
import uz.developers.paypal.service.AuthService;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@Service
public class AuthServiceImpl implements AuthService, UserDetailsService {
    @Autowired
    UserRepository userRepository;

    @Lazy
    @Autowired
    PasswordEncoder passwordEncoder;


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

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        return null;
    }
}
