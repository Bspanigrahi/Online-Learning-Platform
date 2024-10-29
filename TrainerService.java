package com.api.Service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


import com.api.entity.Trainer;
import com.api.repository.TrainerRepository;

import jakarta.transaction.Transactional;



@Service
public class TrainerService {

    @Autowired
    private TrainerRepository trainerRepository;

    
    public Trainer saveTrainer(Trainer trainer) {
        return trainerRepository.save(trainer);
    }

    
    public List<Trainer> getAllTrainers() {
        return trainerRepository.findAll();
    }


	public boolean existsById(Long id) {
		
		return false;
	}


	 @Transactional 
	    public boolean deleteById(Long id) {
	        if (trainerRepository.existsById(id)) {
	            trainerRepository.deleteById(id); 
	            return true;
	        }
	        return false;
	    }


   

    
	
    
}