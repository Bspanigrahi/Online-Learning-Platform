package com.api.Controller;

import java.util.List;

//import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
//import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.api.Service.TrainerService;
import com.api.entity.Trainer;

import jakarta.persistence.EntityNotFoundException;

@RestController
@RequestMapping("/trainerForm")
public class TrainerController {
	
	@Autowired
    private TrainerService trainerService;

    
	 

	    
  
	 @PostMapping("/add")
	    public ResponseEntity<Trainer> createTrainer(@RequestBody Trainer trainer) {
	        Trainer savedTrainer = trainerService.saveTrainer(trainer);
	        if (savedTrainer != null) {
	            return new ResponseEntity<>(savedTrainer, HttpStatus.CREATED);
	        } else {
	            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
	        }
	    }
   
    @GetMapping("/trainers")
    public ResponseEntity<List<Trainer>> getAllTrainer() {
        List<Trainer> trainer = trainerService.getAllTrainers();
        System.out.println(trainer);
        return ResponseEntity.ok(trainer); // Spring Boot auto-converts to valid JSON
    }

    
   
    @DeleteMapping("/trainers/{id}") // Ensure the correct endpoint
    public ResponseEntity<String> deleteTrainer(@PathVariable Long id) {
        if (trainerService.deleteById(id)) {
            return new ResponseEntity<>("trainer deleted successfully", HttpStatus.OK);
        } else {
            return new ResponseEntity<>("Trainer not found", HttpStatus.NOT_FOUND); // Handle not found case
        }
    }
    
   
    
   
    
}
