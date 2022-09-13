package com.revature.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.revature.models.Workouts;
import com.revature.services.ClientService;
import com.revature.services.WorkoutsService;

@RestController
@RequestMapping("/workout") // data/workout/
public class WorkoutsController {
	
	@Autowired
	private WorkoutsService workoutsService;
	@Autowired
	private ClientService clientService;
	
	@PostMapping("/recordWorkout/{id}")
	@CrossOrigin
	public Workouts recordWorkout(@RequestBody Workouts workout, @PathVariable("id") int clientId) {
		return workoutsService.recordWorkout(workout, clientId);
		
	}
	
	@GetMapping("/getWorkouts")
	@CrossOrigin
	public List<Workouts> getWorkouts(){
		return workoutsService.getWorkouts();
		
	}
	
	@GetMapping("/{id}")	
	@CrossOrigin
	public List<Workouts> getWorkouts(@PathVariable("id") int clientId){
		return clientService.getWorkoutsByClientId(clientId);
		
	}
	
	

}
