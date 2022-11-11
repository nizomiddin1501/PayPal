package uz.developers.paypal.security;

import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.springframework.stereotype.Component;

import java.util.Date;
@Component
public class JwtProvider {

     String secretKey = "BuzTokenniMaxfiySuziHechKimBilmasin";
     long expireTime = 36_000_000;


    public String generateToken(String username){
        Date expireDate = new Date(System.currentTimeMillis() + expireTime);
        String token = Jwts
                .builder()
                .setSubject(username)
                .setIssuedAt(new Date())
                .setExpiration(expireDate)
                .signWith(SignatureAlgorithm.HS512, secretKey)
                .compact();
        return token;
    }

    public boolean validateToken(String token){
        try {
            Jwts
                    .parser()
                    .setSigningKey(secretKey)
                    .parseClaimsJws(token);
            return true;
        }catch (Exception e){
            e.printStackTrace();
        }
        return false;
    }

    public String getUserNameFromToken(String token){
        String username = Jwts
                .parser()
                .setSigningKey(secretKey)
                .parseClaimsJws(token)
                .getBody()
                .getSubject();
        return username;
    }

}
