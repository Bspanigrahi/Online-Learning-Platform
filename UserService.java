package com.api.Service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;

import com.api.entity.User;

import com.api.repository.UserRepository;

import java.util.List;


@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;

    public User saveUser(User user) {
        return userRepository.save(user);
    }

    public List<User> findAllUsers() {
        return userRepository.findAll();
    }
     


    public User validateUser(String email, String password) {
        User user = new User();
        if (user != null && user.getPassword().equals(password)) { // Implement proper password hashing in production
            return user;
        }
        return null; 
    }

	
    public User getUserById(Long userId) {
        return userRepository.findById(userId).orElse(null);
    }

	public User getLoginUserInfo(String userEmail) {
		
		return userRepository.findByEmail(userEmail);
	}
	
	
	 public boolean deleteUserById(Long id) {
	        if (userRepository.existsById(id)) {
	            userRepository.deleteById(id); 
	            return true;
	        }
	        return false; 
	    }
	 
	
   
   

}
