package com.gaff.demo;

/*
 * This class controls the site's security, and restricts sections of the site.
 * It currently uses set passwords and usernames for each role, 
 * since the database is not set up yet.
 * Last updated 10/15/2022
 * Author(s): Jessica Frank
 */

import com.gaff.demo.models.Role;
import org.springframework.context.annotation.Bean;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;


@EnableWebSecurity
@EnableGlobalMethodSecurity(prePostEnabled = true, securedEnabled = true, jsr250Enabled = true)
public class SecurityConfig {

    @Bean
    public UserDetailsService userDetailsService() {
        InMemoryUserDetailsManager manager = new InMemoryUserDetailsManager();
        manager.createUser(User.withUsername("player")
            .password("{noop}ppass")
            .roles(Role.player)
            .build());
        manager.createUser(User.withUsername("admin")
            .password("{noop}apass")
            .roles(Role.player, Role.admin)
            .build());
        manager.createUser(User.withUsername("moderator")
            .password("{noop}mpass")
            .roles(Role.player, Role.moderator)
            .build());
        return manager;
    }

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        http
            .csrf().disable()
            .authorizeRequests()
            .antMatchers("/")
                .hasAnyRole(Role.player, Role.admin, Role.moderator)
            .antMatchers("/add_game/**")
                .hasAnyRole(Role.moderator)
            .antMatchers("/edit_game/**")
                .hasAnyRole(Role.moderator)
            .antMatchers("/change_role")
                .hasAnyRole(Role.admin)
            .antMatchers("/view_logs")
                .hasAnyRole(Role.admin)
            .and().formLogin().loginPage("/login").permitAll()
            .and().logout().logoutSuccessUrl("/login/again")
            .and().httpBasic();

        return http.build();
    }
}
