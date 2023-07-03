package com.lipari.app.commons.security.controller;

import com.lipari.app.commons.security.config.JWTGenerator;
import com.lipari.app.commons.security.response.AuthResponse;
import com.lipari.app.users.dto.LogInDto;
import com.lipari.app.users.dto.RegisterDto;
import com.lipari.app.users.entities.Role;
import com.lipari.app.users.entities.User;
import com.lipari.app.users.repositories.RoleRepo;
import com.lipari.app.users.repositories.UserRepo;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Collection;
import java.util.Collections;

@RestController
@RequestMapping("/api/auth")
@RequiredArgsConstructor
public class AuthController {

    private final AuthenticationManager authenticationManager;
    private final UserRepo userRepo;
    private final RoleRepo roleRepo;
    private final PasswordEncoder passwordEncoder;
    private final JWTGenerator jwtGenerator;

    @PostMapping("/login")
    public ResponseEntity<AuthResponse> login(@RequestBody LogInDto logInDto){
       Authentication authentication = authenticationManager.authenticate(
               new UsernamePasswordAuthenticationToken(logInDto.getUsername(),logInDto.getPassword()));
        SecurityContextHolder.getContext().setAuthentication(authentication);
        String token = jwtGenerator.generateToken(authentication);
        return ResponseEntity.ok().body(new AuthResponse(token));
    }
    @PostMapping("/register")
    public ResponseEntity<?> register(@RequestBody RegisterDto registerDto){
        if (userRepo.existsByUsername(registerDto.getUsername())){
            return new ResponseEntity<>("username is taken", HttpStatus.BAD_REQUEST);
        }
        User user = new  User();
        user.setUsername(registerDto.getUsername());
        user.setPassword(passwordEncoder.encode(registerDto.getPassword()));
        Role role = roleRepo.findByName("USER").get();
        user.getRoles().add(role);
        return ResponseEntity.ok().body(userRepo.save(user));
    }
}
