package org.itstep;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;

@EnableWebSecurity
@Configuration

@EnableGlobalMethodSecurity(prePostEnabled = true)
public class WebSecurityConfig {

    @Bean
    public UserDetailsService userDetailsService()
    {
        var userDetailsManager = new InMemoryUserDetailsManager();
        userDetailsManager.createUser(User.withUsername("user").password(passwordEncoder().encode("user")).authorities("part").build());
        userDetailsManager.createUser(User.withUsername("root").password(passwordEncoder().encode("root")).authorities("all").build());
        return userDetailsManager;
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Bean
    public SecurityFilterChain getConfiguration(HttpSecurity httpSecurity) throws Exception {
        return httpSecurity
                .authorizeRequests()
                .antMatchers("/user/**").hasAuthority("part")
                .antMatchers("/root/**").hasAuthority("all")
                .anyRequest().authenticated()
                .and()
                .formLogin()
                .permitAll()
                .and()
                .build();
    }
} 