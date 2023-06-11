package com.lipari.app.controller;

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
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.lipari.app.model.dao.AddressDao;
import com.lipari.app.model.vo.Address;
import com.lipari.app.model.vo.User;
import com.lipari.app.services.UserService;

@RestController
@RequestMapping("/api")
public class UserController {

	private UserService userService;

	@Autowired
	public UserController(UserService userService) {
		this.userService = userService;
	}

	// GET

	@GetMapping("/user/login")
	public User login(@RequestParam String username, @RequestParam String password) {
		return userService.loging(username, password);
	}

	@GetMapping("/user/address/{userId}")
	public List<String> listAddressByUserId(@PathVariable int userId) {
		return userService.adressList(userId);
	}

	// POST

	@PostMapping("/user")
	public boolean addUser(@RequestBody User user) {
		return userService.createUser(user);
	}

	@PostMapping("/user/{userId}/address")
	public boolean addAddress(@PathVariable int userId, @RequestParam String address) {
		return userService.addAddress(userId, address);
	}

	@PostMapping("/user/address")
	public boolean addAddress1(@RequestBody Address address) {
		return userService.addAddress(address);
	}

	/*
	 * @PostMapping("/user/address") public boolean addAddress(@RequestParam int
	 * userId, @RequestParam String address) { return
	 * userService.addAddress(userId,address); }
	 * 
	 * @PostMapping("/user/address1") public boolean addAddress1(@RequestBody
	 * Address address) { return userService.addAddress(address); }
	 */

	// UPDATE

	@PutMapping("/user")
	public boolean updateUser(@RequestBody User user) {
		return userService.changeUser(user);
	}
	
	// DELETE
	@DeleteMapping("/user/{userId}")
	public String deleteUserById(@PathVariable int userId) {

		return "deleted user ID : " + userId+ " " + userService.cancelUser(userId);
	}

	@DeleteMapping("/user/address")
	public String deleteAddress(@RequestBody Address address) {

		return "deleted user ID : " + address.getUserId() + " e indirizzo : " + address.getIndirizzo() + " "
				+ userService.cancelAddress(address.getUserId(), address.getIndirizzo());
	}
}
