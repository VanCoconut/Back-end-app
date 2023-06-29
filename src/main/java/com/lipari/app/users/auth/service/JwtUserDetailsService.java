package com.lipari.app.users.auth.service;

import java.util.List;
import java.util.stream.Collectors;

import com.lipari.app.commons.exception.utils.NotFoundException;
import com.lipari.app.users.dto.UserDto;
import com.lipari.app.users.entities.User;
import com.lipari.app.users.repositories.UserRepo;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class JwtUserDetailsService implements UserDetailsService {

	private final UserRepo userRepo;

	private final PasswordEncoder bcryptEncoder;
	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		User user = userRepo.findByUsername(username).orElseThrow(()->new NotFoundException("user not found"));
		if (user == null) {
			throw new UsernameNotFoundException("User not found with username: " + username);
		}

		List<GrantedAuthority> authorities = user.getRoles().stream()
				.map(
						role -> new SimpleGrantedAuthority(role.getRoleName().name())
						
						).collect(Collectors.toList());

		return new org.springframework.security.core.userdetails.User(user.getUsername(), user.getPassword(),
				authorities);
	}

	public User save(UserDto user) {
		User newUser = new User();
		newUser.setFirstName(user.getFirstName());
		newUser.setLastName(user.getLastName());
		newUser.setUsername(user.getUsername());
		newUser.setPassword(bcryptEncoder.encode(user.getPassword()));
		newUser.setEmail(newUser.getEmail());
		return userRepo.save(newUser);
	}
}