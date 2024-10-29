package com.api.Service;

import org.springframework.stereotype.Service;

import com.api.entity.Admin;
import com.api.repository.AdminRepository;

@Service
public class AdminService {
    
    private final AdminRepository adminRepository;

    public AdminService(AdminRepository adminRepository) {
        this.adminRepository = adminRepository;
    }

    public boolean validateAdmin(String email, String password) {
        
        Admin admin = adminRepository.findByEmail(email);
        return admin != null && admin.getPassword().equals(password); 
    }
}
