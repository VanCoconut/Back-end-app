package com.lipari.app.users.controllers;


import com.lipari.app.users.services.AdminService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

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
