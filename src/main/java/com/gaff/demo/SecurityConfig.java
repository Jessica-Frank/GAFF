package com.gaff.demo;

/*
 * This class controls the site's security, and restricts sections of the site.
 * It currently uses set passwords and usernames for each role, 
 * since the database is not set up yet.
 * Last updated 11/14/2022
 * Author(s): Jessica Frank
 */
import com.gaff.demo.models.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.password.NoOpPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

@EnableWebSecurity
@EnableGlobalMethodSecurity(prePostEnabled = true, securedEnabled = true, jsr250Enabled = true)
public class SecurityConfig {

    @Autowired
    UserRepository userRep;

    @Bean
    public UserDetailsService userDetailsService() {
        return new CustomUserDetailsService();
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return NoOpPasswordEncoder.getInstance();
    }

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        http.csrf().disable()
                .authorizeRequests()
                .antMatchers("/").authenticated()
                .antMatchers("/game_list").authenticated()
                .antMatchers("/genre/**").authenticated()
                .antMatchers("/game/**").authenticated()
                .antMatchers("/user/**").authenticated()
                .antMatchers("/add_game/**").hasRole("MOD")
                .antMatchers("/edit_game/**").hasRole("MOD")
                .antMatchers("/change_role").hasRole("ADM")
                .antMatchers("/view_logs").hasRole("ADM")
                .and().formLogin().loginPage("/login").permitAll()
                .and().logout().logoutSuccessUrl("/login/again")
                .and().httpBasic();

        return http.build();
    }
}
