package com.lipari.app.users.controllers;

import com.lipari.app.users.dto.LogInDto;
import com.lipari.app.users.dto.RegisterDto;
import com.lipari.app.users.entities.Address;
import com.lipari.app.users.entities.AppUser;
import com.lipari.app.users.entities.Role;
import com.lipari.app.users.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/users")
public class UserController {

	private UserService userService;

	@Autowired
	public UserController(UserService userService) {
		this.userService = userService;
	}

	// GET

	@GetMapping("/{id}")
	public AppUser getuserById(@PathVariable Long id) {
		return userService.findUserById(id);
	}

	@GetMapping("/role/{id}")
	public Role getRoleById(@PathVariable Long id) {
		return userService.findRoleById(id);
	}

	@GetMapping("/role/")
	public Role getRoleByDescription(@RequestParam String descr) {
		return userService.findRoleByDescription(descr);
	}

	@GetMapping("/role")
	public List<Role> getAllRole() {
		return userService.findAllRole();
	}

	@GetMapping("/{userId}/address")
	public List<String> listAddressByUserId(@PathVariable Long userId) {
		return userService.adressList(userId);
	}

	@GetMapping("/address/{id}")
	public Address getAddressbyId(@PathVariable Long id) {
		return userService.getAddressById(id);
	}

	// POST

	@PostMapping("/login")
	public AppUser login(@RequestBody LogInDto log) {
		return userService.loging(log.getUsername(), log.getPassword());
	}

	@PostMapping("/save")
	public AppUser addUser(@RequestBody RegisterDto form) {
		return userService.createUser(form);
	}

	@PostMapping("/role")
	public Role addRole(@RequestParam String name) {
		return userService.addRole(name);
	}

	@PostMapping("/{userId}/address")
	public AppUser addAddress(@PathVariable Long userId, @RequestParam String address) {
		return userService.addAddress(userId, address);
	}

	// PUT

	@PutMapping("/{id}")
	public AppUser updateUser(@PathVariable Long id, @RequestBody AppUser appUser) {
		return userService.changeUser(id, appUser);

	}

	@PutMapping("/{id}/password")
	public AppUser updateUserPassword(@PathVariable Long id, @RequestParam String oldPassword,
									  @RequestParam String newPassword, @RequestParam String confirmPassword) {
		return userService.changePassword(id, oldPassword, newPassword, confirmPassword);
	}

	@PutMapping("/role/{id}")
	public Role updateRole(@PathVariable Long id, @RequestBody Role role) {
		return userService.updateRole(id, role);

	}

	// DELETE
	@DeleteMapping("/{userId}")
	public String deleteUserById(@PathVariable Long userId) {
		userService.cancelUser(userId);
		return "deleted user ID : " + userId;
	}

	@DeleteMapping("/role/{id}")
	public String deleteRoleById(@PathVariable Long id) {
		userService.cancelRoleById(id);
		return "deleted role ID : " + id;
	}

	@DeleteMapping("/role/")
	public String deleteRoleByDescription(@RequestParam String descr) {
		userService.cancelRoleByDescription(descr);
		return "deleted role description : " + descr;
	}

	@DeleteMapping("/address/{id}")
	public String deleteAddress(@PathVariable Long id) {
		userService.cancelAddress(id);
		return "deleted address ID : " + id;
	}
}
