package com.api.Controller;



import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.api.Service.CourseService;
import com.api.entity.Course;

@RestController
@RequestMapping("/courseForm")
public class CourseController {

    @Autowired
    private CourseService courseService;

    @PostMapping
    public ResponseEntity<Course> createCourse(@RequestBody Course course) {
        Course savedCourse = courseService.saveCourse(course);
        return new ResponseEntity<>(savedCourse, HttpStatus.CREATED);
    }
    
    
    @GetMapping("/courses")
    public ResponseEntity<List<Course>> getAllCourses() {
        List<Course> courses = courseService.findAllCourses();
        System.out.println(courses);
        return ResponseEntity.ok(courses); // Spring Boot auto-converts to valid JSON
    }


    @DeleteMapping("/delete/{id}") // Endpoint to delete a course by ID
    public ResponseEntity<String> deleteCourse(@PathVariable Long id) {
        try {
            boolean isDeleted = courseService.deleteCourseById(id); // Call the service to delete the course
            if (isDeleted) {
                return ResponseEntity.ok("Course deleted successfully."); // Success response
            } else {
                return ResponseEntity.status(HttpStatus.NOT_FOUND)
                                     .body("Course not found."); // Return error if not found
            }
        } catch (Exception e) {
            // Log the exception
            System.err.println("Error deleting course: " + e.getMessage());
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                                 .body("Error occurred while deleting course."); // Handle server error
        }
    }
    
}

