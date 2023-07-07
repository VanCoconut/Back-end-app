package com.lipari.app.auth.config;


import com.lipari.app.auth.filter.JwtFilter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.BeanIds;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

/**
 * This class is used to confire the endpoints where the client can access with specific authorities
 *
 * @author Vincenzo Catalano
 */
@Configuration
@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter {

    /**
     * UserDetailsService bean created in AppConfig  {@link UserDetailsService#loadUserByUsername(String username)}
     *
     * @see "AppConfig"
     *
     */
    @Autowired
    private UserDetailsService userDetailsService;

    /**
     * JwtFilter component  {@link JwtFilter}
     * @see "JwtFilter"
     *
     */
    @Autowired
    private JwtFilter jwtFilter;


    /**
     * configure using the AuthenticationManagerBuilder
     * @param auth AuthenticationManagerBuilder
     * @throws Exception ex
     * @see "cofigure"
     */
    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.userDetailsService(userDetailsService);
    }


    /**
     * PasswordEncoder bean bcrypt the password
     * @return BCryptPasswordEncoder
     */
    @Bean
    public PasswordEncoder passwordEncoder(){
        return new BCryptPasswordEncoder();
    }

    /**
     * AuthenticationManager bean as standard
     * @return AuthenticationManager
     * @throws Exception ex
     */
    @Bean(name = BeanIds.AUTHENTICATION_MANAGER)
    @Override
    public AuthenticationManager authenticationManagerBean() throws Exception {
        return super.authenticationManagerBean();
    }

    /**
     * Configure the 'filter chain
     * @param  http HttpSecurity
     * @throws Exception ex
     * this method
     * <ul>
     *     <li>disable the csfr</li>
     *     <li>allow the authentication form</li>
     *     <li>allow the admin users only to access the admins APIs</li>
     *     <li>allow the common users only to access the users APIs</li>
     *     <li>set the sessions stateless</li>
     *     <li>add the filter before using the {@link UserDetailsService#loadUserByUsername(String string)}</li>
     * </ul>
     *
     *
     */
    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.csrf().disable().authorizeRequests().antMatchers("/api/auth/**")
                .permitAll().and()
                .authorizeRequests().antMatchers("/api/admins/**").hasAuthority("ROLE_ADMIN")
                .and()
                .authorizeRequests().antMatchers("/api/users/**").hasAuthority("ROLE_USER")
             //   .anyRequest().authenticated()
                .and().exceptionHandling().and().sessionManagement()
                .sessionCreationPolicy(SessionCreationPolicy.STATELESS);
        http.addFilterBefore(jwtFilter, UsernamePasswordAuthenticationFilter.class);;
    }
}
