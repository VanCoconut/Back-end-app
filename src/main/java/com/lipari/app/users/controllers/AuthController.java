package com.lipari.app.users.controllers;

import com.lipari.app.users.dto.AuthRequest;
import com.lipari.app.users.dto.RegisterDto;
import com.lipari.app.users.dto.response.AuthenticationResponse;
import com.lipari.app.users.services.AuthenticationService;
import com.lipari.app.users.services.JwtService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/auth")
@RequiredArgsConstructor
public class AuthController {

    private final JwtService jwtService;
    private final AuthenticationManager authenticationManager;

    private final AuthenticationService service;

//    @PostMapping("/login")
//    public String getToken(@RequestBody AuthRequest request) {
//        Authentication auth = authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(request.getUsername(), request.getPassword()));
//        if (auth.isAuthenticated()) {
//            return jwtService.generateToken(request.getUsername());
//        } else {
//            throw new UsernameNotFoundException("user not foud");
//        }
//
//    }

    @PostMapping("/register")
    public ResponseEntity<AuthenticationResponse> register(@RequestBody RegisterDto request) {
        return ResponseEntity.ok(service.register(request));

    }


    @PostMapping("/login")
    public ResponseEntity<AuthenticationResponse> login(@RequestBody AuthRequest request) {
        return ResponseEntity.ok(service.authenticate(request));

    }
}
