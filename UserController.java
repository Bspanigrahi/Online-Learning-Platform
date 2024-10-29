package com.api.Controller;
import java.util.List;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import com.api.Service.UserService;
import com.api.entity.User;
import com.api.entity.Course;
import com.api.repository.CourseRepository;


import jakarta.servlet.http.HttpSession;

@RestController
@RequestMapping("/userForm")
public class UserController {

    @Autowired
    private UserService userService;
    
    @Autowired
   private CourseRepository courseRepository;

    @PostMapping
    public User createUser(@RequestBody User user) {
        // Check if the course exists
        Course course = courseRepository.findById(user.getCourse().getId()).orElse(null);
        if (course != null) {
            user.setCourse(course);  // Assign the course to the user
        } else {
            throw new RuntimeException("Course not found with ID: " + user.getCourse().getId());
        }

        return userService.saveUser(user);
    }


    @GetMapping("/users")
    public ResponseEntity<List<User>> getAllUsers() {
        List<User> users = userService.findAllUsers();
        //System.out.println(users);
        return ResponseEntity.ok(users); // Spring Boot auto-converts to valid JSON
    }

    @PostMapping("/login")
    public ResponseEntity<User> login(@RequestParam String email, @RequestParam String password, HttpSession session) {
        User user = userService.validateUser(email, password); // Validate the user credentials
        if (user != null) {
            session.setAttribute("user", user); // Store user object in session
            return ResponseEntity.ok(user); // Return user info as response
        } else {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(null);
        }
    }
    
    
   

    @GetMapping("/login-user-info")
    public ResponseEntity<User> getLoginUserInfo(@RequestParam String userEmail){
    	User user = userService.getLoginUserInfo(userEmail);
    	Course course = user.getCourse();
    	course.setCourseName(null);
    	course.setTrainerName(null);
    	course.setPrice(null);
		return ResponseEntity.ok(user);
    	
    }
    
    
    
    


    @PostMapping("/logout")
    public ResponseEntity<String> logout(HttpSession session) {
        session.invalidate();
        return ResponseEntity.ok("User logged out successfully.");
    }
    
    
   
  //  @DeleteMapping("/remove/{id}")
   // public ResponseEntity<String> deleteUser(@PathVariable Long id) {
    //    boolean isDeleted = userService.deleteUserById(id); // Call the service to delete the user
    //    if (isDeleted) {
    //        return ResponseEntity.ok("User deleted successfully.");
    //    } else {
     //       return ResponseEntity.status(HttpStatus.NOT_FOUND)
     //                            .body("User not found.");
     //   }
   // }

    // Endpoint to get all users who are not removed
   
    @DeleteMapping("/users/{id}")
    public ResponseEntity<String> deleteUser(@PathVariable Long id) {
        userService.deleteUserById(id);
        return new ResponseEntity<>("User deleted successfully", HttpStatus.OK);
    }
}
