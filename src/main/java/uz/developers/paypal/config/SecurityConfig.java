package uz.developers.paypal.config;

import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import uz.developers.paypal.security.JwtFilter;
import uz.developers.paypal.service.AuthService;
import uz.developers.paypal.service.impl.AuthServiceImpl;

@Configuration
@EnableWebSecurity
@RequiredArgsConstructor
public class SecurityConfig extends WebSecurityConfigurerAdapter {

    @Autowired
    AuthServiceImpl authServiceImpl;
    @Autowired
    JwtFilter jwtFilter;


    // SUPER_ADMIN, ADMIN, USER

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.userDetailsService(authServiceImpl);
    }


    private final PasswordEncoder passwordEncoder;
    @Override
    protected void configure(HttpSecurity http) throws Exception {

        http

                .csrf().disable()
                .authorizeRequests()
                .antMatchers("/","index","/css/*","/js/*").permitAll()
                //.antMatchers("/","/api/login").permitAll()
                .antMatchers(HttpMethod.GET,"/api/**").hasAnyRole(UserRole.USER.name(),UserRole.ADMIN.name(),UserRole.SUPER_ADMIN.name())
                .antMatchers(HttpMethod.POST,"/api/**").hasAnyRole(UserRole.USER.name(),UserRole.ADMIN.name(),UserRole.SUPER_ADMIN.name())
                .antMatchers(HttpMethod.PUT,"/api/**").hasAnyRole(UserRole.ADMIN.name(),UserRole.SUPER_ADMIN.name())
                .antMatchers(HttpMethod.DELETE,"/api/**").hasAnyRole(UserRole.SUPER_ADMIN.name())
                .anyRequest()
                .authenticated()
                .and()
                .httpBasic();

        // bu user servisga kigandan keyin unga token beriladi va bu tokeni requst bilan hedirga keladi shu vaqtd asecuritiy birinchi jwt filtrga boradi default holda UsernamePasswordAuthenticationFilter shunga boradi
        http.addFilterBefore(jwtFilter, UsernamePasswordAuthenticationFilter.class);
        // bu token began vaqtda ssisinoni o'chirib qoyadi jwt tokeni o'chirilgan holda ham session ishlab turadi bu xatoto yani token muddati tugagan bo'lishi mumkin
        http.sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS);

    }


    // user bazada bor yoki yo'qligini ko'radigan metod
    @Bean
    @Override
    protected AuthenticationManager authenticationManager() throws Exception {
        return super.authenticationManager();
    }



    @Override
    @Bean
    protected UserDetailsService userDetailsService() {
        UserDetails user = User.builder()
                .username("user")
                .password(passwordEncoder.encode("user"))
                .roles(UserRole.USER.name()) //ROLE_USER
                .build();

        UserDetails admin = User.builder()
                .username("admin")
                .password(passwordEncoder.encode("admin"))
                .roles(UserRole.ADMIN.name()) //ROLE_ADMIN
                .build();

        UserDetails super_admin = User.builder()
                .username("super_admin")
                .password(passwordEncoder.encode("super_admin"))
                .roles(UserRole.SUPER_ADMIN.name()) //ROLE_SUPER_ADMIN
                .build();


        return new InMemoryUserDetailsManager(
                user,
                admin,
                super_admin

        );
    }

}
