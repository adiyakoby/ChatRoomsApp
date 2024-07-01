package com.ex5adiyakobymichaelzargari.springSecurity;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.core.session.SessionRegistry;
import org.springframework.security.core.session.SessionRegistryImpl;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.session.RegisterSessionAuthenticationStrategy;
import org.springframework.security.web.authentication.session.SessionAuthenticationStrategy;
import org.springframework.security.web.session.HttpSessionEventPublisher;

import static org.springframework.security.config.Customizer.withDefaults;

/**
 * SecurityJavaConfig configures the security settings for the application using Java configuration.
 */
@Configuration
public class SecurityJavaConfig {

    /**
     * Bean for HttpSessionEventPublisher.
     *
     * @return HttpSessionEventPublisher implementation
     */
    @Bean
    public HttpSessionEventPublisher httpSessionEventPublisher() {
        return new HttpSessionEventPublisher();
    }

    /**
     * Bean for SessionRegistry.
     *
     * @return SessionRegistry implementation
     */
    @Bean
    public SessionRegistry sessionRegistry() {
        return new SessionRegistryImpl();
    }

    /**
     * Bean for password encoding using BCrypt.
     *
     * @return PasswordEncoder implementation
     */
    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    /**
     * Configures the security filter chain.
     *
     * @param http HttpSecurity configuration
     * @return the SecurityFilterChain
     * @throws Exception in case of any configuration issues
     */
    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        http
                .cors(withDefaults())
                .csrf(withDefaults())
                .authorizeHttpRequests((requests) -> requests
                        .requestMatchers("/admin/**").hasRole("ADMIN")
                        .requestMatchers( "/chatroom/**").hasAnyRole("USER", "ADMIN")
                        .requestMatchers( "/*").permitAll()
                        .anyRequest().authenticated()


                )
                .formLogin((form) -> form
                                .loginPage("/login")
                                .defaultSuccessUrl("/", true)
                                .permitAll()
                )
                .logout((logout) -> logout.permitAll())
                .exceptionHandling(
                        (exceptionHandling) -> exceptionHandling
                                .accessDeniedPage("/403")
                )
                .sessionManagement( (sessionManagement) -> sessionManagement
                        .maximumSessions(1)
                        .sessionRegistry(sessionRegistry()).expiredUrl("/banned"));

        return http.build();
    }

    /**
     * Bean for SessionAuthenticationStrategy.
     *
     * @param sessionRegistry the session registry
     * @return SessionAuthenticationStrategy implementation
     */
    @Bean
    public SessionAuthenticationStrategy sessionAuthenticationStrategy(SessionRegistry sessionRegistry) {
        return new RegisterSessionAuthenticationStrategy(sessionRegistry);
    }
}