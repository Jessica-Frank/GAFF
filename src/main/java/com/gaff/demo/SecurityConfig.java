package com.gaff.demo;

/*
 * This class controls the site's security, and restricts sections of the site.
 * It currently uses set passwords and usernames for each role, 
 * since the database is not set up yet.
 * Last updated 10/28/2022
 * Author(s): Jessica Frank
 */
import com.gaff.demo.models.AppUser;
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
            .roles(AppUser.ROLE_PLAYER)
            .build());
        manager.createUser(User.withUsername("admin")
            .password("{noop}apass")
            .roles(AppUser.ROLE_ADMIN)
            .build());
        manager.createUser(User.withUsername("moderator")
            .password("{noop}mpass")
            .roles(AppUser.ROLE_MODERATOR)
            .build());
        return manager;
    }

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        http
            .csrf().disable()
            .authorizeRequests()
            .antMatchers("/").authenticated()
            .antMatchers("/game_list").authenticated()
            .antMatchers("/game_details").authenticated()
            .antMatchers("/player_profile").authenticated()
            .antMatchers("/add_game/**").hasAnyRole(AppUser.ROLE_MODERATOR)
            .antMatchers("/edit_game/**").hasAnyRole(AppUser.ROLE_MODERATOR)
            .antMatchers("/change_role").hasAnyRole(AppUser.ROLE_ADMIN)
            .antMatchers("/view_logs").hasAnyRole(AppUser.ROLE_ADMIN)
            .and().formLogin().loginPage("/login").permitAll()
            .and().logout().logoutSuccessUrl("/login/again")
            .and().httpBasic();

        return http.build();
    }
}
