package com.lipari.app.users.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import com.lipari.app.users.entities.Address;
import com.lipari.app.users.entities.User;
import com.lipari.app.users.dto.LoggInDto;
import com.lipari.app.users.services.UserService;

@RestController
@RequestMapping("/api/users")
public class UserController {

	private UserService userService;

	@Autowired
	public UserController(UserService userService) {
		this.userService = userService;
	}

	// GET

	@PostMapping("/login") 
	public User login(@RequestBody LoggInDto log) {
		return userService.loging(log.getUsername(), log.getPassword());
	}

	@GetMapping("/{userId}/address")
	public List<String> listAddressByUserId(@PathVariable Integer userId) {
		return userService.adressList(userId);
	}

	// POST

	@PostMapping("/")
	public boolean addUser(@RequestBody User user) {
		return userService.createUser(user);
	}

	@PostMapping("/{userId}/address")
	public boolean addAddress(@PathVariable Integer userId, @RequestParam String address) {
		return userService.addAddress(userId, address);
	}

	@PostMapping("/address")
	public boolean addAddress1(@RequestBody Address address) {
		return userService.addAddress(address);
	}

	@PutMapping("/user")
	public boolean updateUser(@RequestBody User user) {
		return userService.changeUser(user);
	}

	// DELETE
	@DeleteMapping("/user/{userId}")
	public String deleteUserById(@PathVariable int userId) {

		return "deleted user ID : " + userId + " " + userService.cancelUser(userId);
	}

	@DeleteMapping("/user/address")
	public String deleteAddress(@RequestBody Address address) {

		return "deleted user ID : " + address.getUserId() + " e indirizzo : " + address.getIndirizzo() + " "
				+ userService.cancelAddress(address.getUserId(), address.getIndirizzo());
	}
}
