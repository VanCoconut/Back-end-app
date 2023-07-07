package com.lipari.app.users.services;

import com.lipari.app.commons.exception.utils.NotFoundException;
import com.lipari.app.users.dto.AuthRequest;
import com.lipari.app.users.dto.RegisterDto;
import com.lipari.app.users.dto.response.AuthenticationResponse;
import com.lipari.app.users.entities.AppUser;
import com.lipari.app.users.repositories.RoleRepo;
import com.lipari.app.users.repositories.UserRepo;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

/**
 * The type Authentication service.
 */
@Service
@RequiredArgsConstructor
public class AuthenticationService {

    /**
     * The User repo.
     */
    private final UserRepo userRepo;

    /**
     * The Password encoder.
     */
    private final PasswordEncoder passwordEncoder;

    /**
     * The Jwt service.
     */
    private final JwtService jwtService;

    /**
     * The Authentication manager.
     */
    private final AuthenticationManager authenticationManager;

    /**
     * The Role repo.
     */
    private final RoleRepo roleRepo;

    /**
     * Register authentication response.
     *
     * @param request the request
     * @return the authentication response
     */
    public AuthenticationResponse register(RegisterDto request) {
        AppUser user = new AppUser();
        user.setNome(request.getFirstname());
        user.setCognome(request.getLastname());
        user.setUsername(request.getUsername());
        user.setPassword(passwordEncoder.encode(request.getPassword()));
        user.getRoles().add(
                roleRepo.findByName(request.getRole()).orElseThrow(()->new NotFoundException("role name not found")));
        userRepo.save(user);
        var jwtToken = jwtService.generateToken(request.getUsername());
        return AuthenticationResponse.builder()
                .token(jwtToken)
                .build();
    }

    /**
     * Authenticate authentication response.
     *
     * @param request the request
     * @return the authentication response
     */
    public AuthenticationResponse authenticate(AuthRequest request) {
        authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(
                        request.getUsername(),
                        request.getPassword()
                )
        );
        var user = userRepo.findByUsername(request.getUsername())
                .orElseThrow(()->new NotFoundException("user not found"));
        var jwtToken = jwtService.generateToken(request.getUsername());
        return AuthenticationResponse.builder()
                .token(jwtToken)
                .build();
    }
}