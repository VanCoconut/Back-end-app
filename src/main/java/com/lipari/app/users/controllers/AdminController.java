package com.lipari.app.users.controllers;


import com.lipari.app.orders.entities.Order;
import com.lipari.app.users.services.AdminService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/admins")
public class AdminController {

    AdminService adminService;

    @Autowired
    public AdminController(AdminService adminService){
        this.adminService=adminService;
    }

	@GetMapping("/hello")
	//@PreAuthorize("hasAuthority('ROLE_ADMIN')")
	public String hello(){
		return "hello";
	}

	/*
	 * @GetMapping("/get-user-by-id/{idUser}") public ResponseEntity<?>
	 * getUserById(@PathVariable int idUser){ return
	 * adminService.getUserById(idUser); }
	 * 
	 * @GetMapping("/retrive-Order") public List<Order> retrieveAdminOrders(){
	 * return adminService.retrieveAdminOrders(); }
	 */

}
