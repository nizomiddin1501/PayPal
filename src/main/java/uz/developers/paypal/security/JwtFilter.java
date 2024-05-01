package uz.developers.paypal.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;
import uz.developers.paypal.service.AuthService;
import uz.developers.paypal.service.impl.AuthServiceImpl;


import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
@Component
public class JwtFilter extends OncePerRequestFilter {
    @Autowired
    JwtProvider jwtProvider;
    @Autowired
    AuthServiceImpl authServiceImpl;
    @Override
    protected void doFilterInternal(HttpServletRequest request,
                                    HttpServletResponse response,
                                    FilterChain filterChain) throws ServletException, IOException {

        //REQUESTDAN TOKENNI OLISH
        String token = request.getHeader("Authorization");

        //TOKEN BORLIGINI VA TOKENNING BOSHLANISHI BEARER BULISHINI TEKSHIRISH
        if (token!=null&&token.startsWith("Bearer")){

            //AYNAN TOKENNI UZINI QIRQIB OLDIK
            token=token.substring(7);

            //TOKENNI VALIDATSIYADAN UTKAZDIK (TOKEN BUZILMAGANINI, MUDDATI O'TMAGANLIGINI VA HOKAZO)
            boolean validateToken = jwtProvider.validateToken(token);
            if (validateToken){

                //TOKENNI ICHIDAN USERNAME NI OLDIK
                String userNameFromToken = jwtProvider.getUserNameFromToken(token);

                //USERNAME ORQALI USERDETAILSNI OLDIK
                UserDetails userDetails = authServiceImpl.loadUserByUsername(userNameFromToken);

                //USERDETAILS ORQALI AUTHENTICATION YARATIB OLDIK
                UsernamePasswordAuthenticationToken usernamePasswordAuthenticationToken =
                        new UsernamePasswordAuthenticationToken(
                                userDetails,
                                null,
                                userDetails.getAuthorities());
                System.out.println(SecurityContextHolder.getContext().getAuthentication());

                //SISTEMAGA KIM KIRGANLIGINI O'RNATDIK
                SecurityContextHolder.getContext().setAuthentication(usernamePasswordAuthenticationToken);

                System.out.println(SecurityContextHolder.getContext().getAuthentication().getPrincipal());
            }

        }
        filterChain.doFilter(request,response);
    }
}
