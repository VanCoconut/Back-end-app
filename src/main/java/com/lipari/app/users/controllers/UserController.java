package com.lipari.app.users.controllers;

import java.util.List;

import com.lipari.app.users.dto.RegisterDto;
import com.lipari.app.users.entities.AppUser;
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

import com.lipari.app.users.dto.LogInDto;
import com.lipari.app.users.entities.Address;
import com.lipari.app.users.entities.Role;
import com.lipari.app.users.services.UserService;

/**
 * The type User controller.
 */
@RestController
@RequestMapping("/api/users")
public class UserController {

    /**
     * The User service.
     */
    private UserService userService;

    /**
     * Instantiates a new User controller.
     *
     * @param userService the user service
     */
    @Autowired
	public UserController(UserService userService) {
		this.userService = userService;
	}

	// GET

    /**
     * Gets by id.
     *
     * @param id the id
     * @return the by id
     */
    @GetMapping("/{id}")
	public AppUser getuserById(@PathVariable Long id) {
		return userService.findUserById(id);
	}

    /**
     * Gets role by id.
     *
     * @param id the id
     * @return the role by id
     */
    @GetMapping("/role/{id}")
	public Role getRoleById(@PathVariable Long id) {
		return userService.findRoleById(id);
	}

    /**
     * Gets role by description.
     *
     * @param descr the descr
     * @return the role by description
     */
    @GetMapping("/role/")
	public Role getRoleByDescription(@RequestParam String descr) {
		return userService.findRoleByDescription(descr);
	}

    /**
     * Gets all role.
     *
     * @return the all role
     */
    @GetMapping("/role")
	public List<Role> getAllRole() {
		return userService.findAllRole();
	}

    /**
     * List address by user id list.
     *
     * @param userId the user id
     * @return the list
     */
    @GetMapping("/{userId}/address")
	public List<String> listAddressByUserId(@PathVariable Long userId) {
		return userService.adressList(userId);
	}

    /**
     * Gets addressby id.
     *
     * @param id the id
     * @return the addressby id
     */
    @GetMapping("/address/{id}")
	public Address getAddressbyId(@PathVariable Long id) {
		return userService.getAddressById(id);
	}

	// POST

    /**
     * Login app user.
     *
     * @param log the log
     * @return the app user
     */
    @PostMapping("/login")
	public AppUser login(@RequestBody LogInDto log) {
		return userService.loging(log.getUsername(), log.getPassword());
	}

    /**
     * Add user app user.
     *
     * @param form the form
     * @return the app user
     */
    @PostMapping("/save")
	public AppUser addUser(@RequestBody RegisterDto form) {
		return userService.createUser(form);
	}

    /**
     * Add role role.
     *
     * @param name the name
     * @return the role
     */
    @PostMapping("/role")
	public Role addRole(@RequestParam String name) {
		return userService.addRole(name);
	}

    /**
     * Add address app user.
     *
     * @param userId  the user id
     * @param address the address
     * @return the app user
     */
    @PostMapping("/{userId}/address")
	public AppUser addAddress(@PathVariable Long userId, @RequestParam String address) {
		return userService.addAddress(userId, address);
	}

	// PUT

    /**
     * Update user app user.
     *
     * @param id      the id
     * @param appUser the app user
     * @return the app user
     */
    @PutMapping("/{id}")
	public AppUser updateUser(@PathVariable Long id, @RequestBody AppUser appUser) {
		return userService.changeUser(id, appUser);

	}

    /**
     * Update user password app user.
     *
     * @param id              the id
     * @param oldPassword     the old password
     * @param newPassword     the new password
     * @param confirmPassword the confirm password
     * @return the app user
     */
    @PutMapping("/{id}/password")
	public AppUser updateUserPassword(@PathVariable Long id, @RequestParam String oldPassword,
									  @RequestParam String newPassword, @RequestParam String confirmPassword) {
		return userService.changePassword(id, oldPassword, newPassword, confirmPassword);
	}

    /**
     * Update role role.
     *
     * @param id   the id
     * @param role the role
     * @return the role
     */
    @PutMapping("/role/{id}")
	public Role updateRole(@PathVariable Long id, @RequestBody Role role) {
		return userService.updateRole(id, role);

	}

    /**
     * Delete user by id string.
     *
     * @param userId the user id
     * @return the string
     */
// DELETE
	@DeleteMapping("/{userId}")
	public String deleteUserById(@PathVariable Long userId) {
		userService.cancelUser(userId);
		return "deleted user ID : " + userId;
	}

    /**
     * Delete role by id string.
     *
     * @param id the id
     * @return the string
     */
    @DeleteMapping("/role/{id}")
	public String deleteRoleById(@PathVariable Long id) {
		userService.cancelRoleById(id);
		return "deleted role ID : " + id;
	}

    /**
     * Delete role by description string.
     *
     * @param descr the descr
     * @return the string
     */
    @DeleteMapping("/role/")
	public String deleteRoleByDescription(@RequestParam String descr) {
		userService.cancelRoleByDescription(descr);
		return "deleted role description : " + descr;
	}

    /**
     * Delete address string.
     *
     * @param id the id
     * @return the string
     */
    @DeleteMapping("/address/{id}")
	public String deleteAddress(@PathVariable Long id) {
		userService.cancelAddress(id);
		return "deleted address ID : " + id;
	}
}
