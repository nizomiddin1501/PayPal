package uz.developers.paypal.config;

import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;

@Configuration
@EnableWebSecurity
@RequiredArgsConstructor
public class SecurityConfig extends WebSecurityConfigurerAdapter {

    // SUPER_ADMIN, ADMIN, USER

    private final PasswordEncoder passwordEncoder;
    @Override
    protected void configure(HttpSecurity http) throws Exception {

        http

                .csrf().disable()
                .authorizeRequests()
                .antMatchers("/","index","/css/*","/js/*").permitAll()
                .antMatchers(HttpMethod.GET,"/api/**").hasAnyRole(UserRole.USER.name(),UserRole.ADMIN.name(),UserRole.SUPER_ADMIN.name())
                .antMatchers(HttpMethod.POST,"/api/**").hasAnyRole(UserRole.USER.name(),UserRole.ADMIN.name(),UserRole.SUPER_ADMIN.name())
                .antMatchers(HttpMethod.PUT,"/api/**").hasAnyRole(UserRole.ADMIN.name(),UserRole.SUPER_ADMIN.name())
                .antMatchers(HttpMethod.DELETE,"/api/**").hasAnyRole(UserRole.SUPER_ADMIN.name())
                .anyRequest()
                .authenticated()
                .and()
                .httpBasic();
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
