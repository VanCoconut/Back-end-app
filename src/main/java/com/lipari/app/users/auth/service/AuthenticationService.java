package com.lipari.app.users.auth.service;

import org.springframework.security.core.userdetails.User;
import org.springframework.stereotype.Service;

import com.lipari.app.users.auth.dto.request.AuthenticationRequest;
import com.lipari.app.users.auth.dto.request.RegisterRequest;
import com.lipari.app.users.auth.dto.response.AuthenticationResponse;
import com.lipari.app.users.repositories.UserRepo;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class AuthenticationService {
	
	private final UserRepo userRepo;

	public AuthenticationResponse register(RegisterRequest request) {
		var user = User.builder()
			.
				
		return null;
	}

	public AuthenticationResponse authenticate(AuthenticationRequest request) {
	
		return null;
	}

	
}
