package com.lipari.app.users.controllers;


import com.lipari.app.commons.exception.utils.DataException;
import com.lipari.app.commons.exception.utils.InvalidDataException;
import com.lipari.app.commons.exception.utils.NotFoundException;
import com.lipari.app.commons.exception.utils.ValidationException;
import com.lipari.app.orders.entities.Order;
import com.lipari.app.users.entities.Address;
import com.lipari.app.users.entities.Role;
import com.lipari.app.users.entities.User;
import com.lipari.app.users.services.AdminService;
import com.lipari.app.users.services.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RequiredArgsConstructor
@RestController
@PreAuthorize(" hasRole('ADMIN')")
@RequestMapping("/api/admins")

public class AdminController {

    private final AdminService adminService;

    private final UserService userService;

    @GetMapping("/users")
    public List<User> getAllUser() {
        return adminService.findAllUser();
    }

	@PostMapping("/users/AddRoleUser/{id}")
	public User addRoleForUser (@PathVariable Long id) {
		return adminService.addRoleForUser(id);
	}

    @PostMapping("/newRole")
    public Role addRole(@RequestParam String name) {
        return adminService.addRole(name);
    }
}
