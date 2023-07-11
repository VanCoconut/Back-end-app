package com.lipari.app.users.controllers;

import com.lipari.app.users.dto.AuthRequest;
import com.lipari.app.users.dto.RegisterDto;
import com.lipari.app.users.dto.response.AuthenticationResponse;
import com.lipari.app.users.services.AuthenticationService;
import com.lipari.app.users.services.JwtService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * The  Auth controller holds the registration and log in http methods.
 */
@RestController
@RequestMapping("/api/auth")
@RequiredArgsConstructor
public class AuthController {

    /**
     * The Jwt service.
     */
    private final JwtService jwtService;
    /**
     * The Authentication manager.
     */
    private final AuthenticationManager authenticationManager;

    /**
     * The Service.
     */
    private final AuthenticationService service;

/*   was replaced by the other one because of its length
*
* */
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

    /**
     * Register http method.
     *
     * @param request the request
     * @return the response entity
     */
    @PostMapping("/register")
    public ResponseEntity<AuthenticationResponse> register(@RequestBody RegisterDto request) {
        return ResponseEntity.ok(service.register(request));

    }


    /**
     * Login http method.
     *
     * @param request the request
     * @return the response entity
     */
    @PostMapping("/login")
    public ResponseEntity<AuthenticationResponse> login(@RequestBody AuthRequest request) {
        return ResponseEntity.ok(service.authenticate(request));

    }
}
