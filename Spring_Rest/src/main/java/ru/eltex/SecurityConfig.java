package ru.eltex;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;

@Configuration
@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter {
    @Autowired
    public void configureGlobal(AuthenticationManagerBuilder auth) throws Exception {
// пользователи через оперативную память
        auth.inMemoryAuthentication()
                .withUser("admin").password("{noop}123").roles("ADMIN");
    }
    @Override
    protected void configure(HttpSecurity http) throws Exception {
// распределение прав
        http.authorizeRequests()
                .antMatchers("/admin").access("hasRole('ROLE_ADMIN')")
                .and()
                .formLogin()
                .defaultSuccessUrl("/");
    }
}