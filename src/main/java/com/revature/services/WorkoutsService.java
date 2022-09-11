package com.revature.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.revature.models.Workouts;
import com.revature.repositories.WorkoutsRepo;

@Service
public class WorkoutsService {

	@Autowired
	private WorkoutsRepo workoutRepo;
	
	public Workouts recordWorkout(Workouts workout) {
		return workoutRepo.save(workout);
	}
	
	
	public List<Workouts> getWorkouts(){
		return (List<Workouts>) workoutRepo.findAll();
		
	}
	
}
