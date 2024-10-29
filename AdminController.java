package com.api.Controller;

import jakarta.servlet.http.HttpSession;


import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.api.Service.AdminService;


@RestController
@RequestMapping("/admin")
public class AdminController {
	
	

    private final AdminService adminService;

    public AdminController(AdminService adminService) {
        this.adminService = adminService;
    }

    @PostMapping("/login")
    public ResponseEntity<String> login(@RequestParam String email, @RequestParam String password, HttpSession session) {
        // Validate the admin credentials
        if (adminService.validateAdmin(email, password)) {
            session.setAttribute("admin", true);
            return ResponseEntity.ok("Admin logged in successfully.");
        } else {
            return ResponseEntity.status(401).body("Invalid credentials");
        }
    }

    @PostMapping("/logout")
    public ResponseEntity<String> logout(HttpSession session) {
        session.invalidate();
        return ResponseEntity.ok("Admin logged out successfully.");
    }
    
    
}
