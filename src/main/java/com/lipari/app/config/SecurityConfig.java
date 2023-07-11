package com.lipari.app.config;


import com.lipari.app.filter.JwtAuthFilter;
import jakarta.servlet.FilterChain;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

/**
 * The  Security config where setting up the security beans and the securityFilterChain.
 */
@Configuration
@EnableWebSecurity
@EnableMethodSecurity
@RequiredArgsConstructor
public class SecurityConfig {
    /**
     * The Auth filter.
     */
    @Autowired
    private final JwtAuthFilter authFilter;

    /**
     * The Authenticcation provider.
     */
    private final AuthenticationProvider authenticcationProvider;

    /**
     * Security filter is divided in 3 main points.
     * <ul>
     *     <li>permit  registration and login</li>
     *     <li>allow only who have the rightful authority to access at some endpoints</li>
     *     <li>set the connection type on STATELESS</li>
     *     <li>set the authentication provider {@link AppConfig#authenticationProvider()}</li>
     *     <li>set the jwt filter {@link JwtAuthFilter}</li>
     * </ul>
     *
     * @param http the http
     * @return the security filter chain
     * @throws Exception the exception
     */
    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        return http.csrf().disable()
                .authorizeHttpRequests()
                .requestMatchers("/api/auth/**").permitAll()
                .and()
                .authorizeHttpRequests()
                .requestMatchers("/api/admins/**").hasAuthority("ROLE_ADMIN")
                .and()
                .authorizeHttpRequests()
                .requestMatchers("/api/users/**").hasAuthority("ROLE_USER")
                //.authenticated()
                .anyRequest()
                .authenticated().and()
                .sessionManagement()
                .sessionCreationPolicy(SessionCreationPolicy.STATELESS)
                .and()
                .authenticationProvider(authenticcationProvider)
                .addFilterBefore(authFilter, UsernamePasswordAuthenticationFilter.class)
                .build();
    }



}
