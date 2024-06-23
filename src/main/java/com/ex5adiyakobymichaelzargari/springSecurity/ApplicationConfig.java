package com.ex5adiyakobymichaelzargari.springSecurity;

import javax.sql.DataSource;

import com.ex5adiyakobymichaelzargari.tabels.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityCustomizer;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.JdbcUserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;
import static org.springframework.security.config.Customizer.withDefaults;

@Configuration
public class ApplicationConfig  {

    @Autowired
    UserRepository userRepository;

    @Autowired
    private DataSource dataSource; // Autowire your DataSource for JDBC operations




    @Bean
    public UserDetailsService userDetailsService(PasswordEncoder bCryptPasswordEncoder) {
        JdbcUserDetailsManager manager = new JdbcUserDetailsManager();
        manager.setDataSource(dataSource);


        // Enable authorities management
        manager.setEnableAuthorities(true); // Enable authorities management
        manager.setEnableGroups(false); // Disable groups management

        // Create users with roles
        if (!manager.userExists("admin")) {
            manager.createUser(User.withUsername("admin")
                    .password(passwordEncoder().encode("admin"))
                    .roles("ADMIN").build());
        }
        if (!manager.userExists("test1")) {
            manager.createUser(User.withUsername("test1")
                    .password(passwordEncoder().encode("test1"))
                    .roles("USER").build());
        }

        return manager;
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }


    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        http
                .cors(withDefaults())
                .csrf(withDefaults())

                .authorizeHttpRequests((requests) -> requests
                        .requestMatchers( "/",  "/error", "/403", "/signup").permitAll()
                        .requestMatchers("/chatroom").hasAnyRole("USER", "ADMIN").anyRequest().authenticated()

                )
                .formLogin((form) -> form
                                .loginPage("/login")
//                                .loginProcessingUrl("/login")
                                .defaultSuccessUrl("/", true)
//                                .failureUrl("/login?error=true")
                                .permitAll()
                )
                .logout((logout) -> logout.permitAll())
                .exceptionHandling(
                        (exceptionHandling) -> exceptionHandling
                                .accessDeniedPage("/403")
                )

        ;

        return http.build();

    }

//    @Autowired
//    public void configureGlobal(AuthenticationManagerBuilder auth) throws Exception {
//        auth.userDetailsService(userDetailsService()).passwordEncoder(passwordEncoder());
//    }



}
