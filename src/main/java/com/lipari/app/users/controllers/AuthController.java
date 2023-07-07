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

/**
 * The type Auth controller.
 */
@RestController
@RequestMapping("/api/auth")
@RequiredArgsConstructor
public class AuthController {

    /**
     * The Service.
     */
    private final AuthenticationService service;

    /**
     * Register response entity.
     *
     * @param request the request {@link  RegisterDto}
     * @return the response entity {@link  AuthenticationResponse}
     */
    @PostMapping("/register")
    public ResponseEntity<AuthenticationResponse> register(@RequestBody RegisterDto request) {
        return ResponseEntity.ok(service.register(request));

    }


    /**
     * Login response entity.
     *
     * @param request the request {@link  AuthRequest}
     * @return the response entity {@link  AuthenticationResponse}
     */
    @PostMapping("/login")
    public ResponseEntity<AuthenticationResponse> login(@RequestBody AuthRequest request) {
        return ResponseEntity.ok(service.authenticate(request));

    }
}
