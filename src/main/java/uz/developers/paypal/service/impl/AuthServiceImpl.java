package uz.developers.paypal.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import uz.developers.paypal.service.AuthService;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@Service
public class AuthServiceImpl implements AuthService, UserDetailsService {

    @Lazy
    @Autowired
    PasswordEncoder passwordEncoder;


    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        List<User> users = new ArrayList<>(
                Arrays.asList(
                        new User("Musobek",passwordEncoder.encode("Absd1209$"),new ArrayList<>()),
                        new User("Doston",passwordEncoder.encode("Absd1222$"),new ArrayList<>()),
                        new User("Fayoz",passwordEncoder.encode("Absd1221$"),new ArrayList<>()),
                        new User("Muxi",passwordEncoder.encode("Absd1223$"),new ArrayList<>())

                ));
        for (User user : users){
            if (user.getUsername().equals(username))
                return user;
        }
        throw new UsernameNotFoundException("User topilmadi");

    }
}
