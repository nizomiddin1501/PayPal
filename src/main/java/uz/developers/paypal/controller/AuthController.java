package uz.developers.paypal.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.web.bind.annotation.*;
import uz.developers.paypal.payload.ApiResponce;
import uz.developers.paypal.payload.LoginDto;
import uz.developers.paypal.security.JwtProvider;
import uz.developers.paypal.service.AuthService;

import java.util.List;

@RestController
@RequestMapping("/api/auth")
public class AuthController {
    @Autowired
    AuthService userService;
    @Autowired
    JwtProvider jwtProvider;
    @Autowired
    AuthenticationManager authenticationManager;


    @PostMapping
    public ResponseEntity<?> loginTo(@RequestBody LoginDto loginDto) {
        try {
            authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(
                    loginDto.getUsername(),
                    loginDto.getPassword()));
            String jwtToken = jwtProvider.generateToken(loginDto.getUsername());
            return ResponseEntity.ok(jwtToken);
        }catch (BadCredentialsException e){
            return ResponseEntity.status(401).body("Login or password is error!");
        }

    }



}
