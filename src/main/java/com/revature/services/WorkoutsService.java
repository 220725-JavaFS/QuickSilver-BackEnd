package com.revature.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.revature.models.Client;
import com.revature.models.Workouts;
import com.revature.repositories.ClientRepo;
import com.revature.repositories.WorkoutsRepo;

@Service
public class WorkoutsService {

	@Autowired
	private WorkoutsRepo workoutRepo;
	@Autowired
	private ClientRepo clientRepo;
	
	public Workouts recordWorkout(Workouts workout, int clientId) {
		Client client = clientRepo.findById(clientId).get();
		client.getWorkouts().add(workout);
		clientRepo.save(client);
		return workout;
	}
	
	
	public List<Workouts> getWorkouts(){
		return (List<Workouts>) workoutRepo.findAll();
		
	}
	

}
