package com.lipari.app.controllers;


import com.lipari.app.model.vo.Order;
import com.lipari.app.services.AdminService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/admin")
public class AdminController {

    AdminService adminService;

    @Autowired
    public AdminController(AdminService adminService){
        this.adminService=adminService;
    }

    @GetMapping("/get-user-by-id/{idUser}")
    public ResponseEntity<?> getUserById(@PathVariable int idUser){
        return adminService.getUserById(idUser);
    }

    @GetMapping("/retrive-Order")
    public List<Order> retrieveAdminOrders(){
        return adminService.retrieveAdminOrders();
    }

}
