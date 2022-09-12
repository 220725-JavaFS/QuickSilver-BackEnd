package com.revature.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.revature.models.Workouts;
import com.revature.services.WorkoutsService;

@RestController
@RequestMapping("/workout") // data/workout/
public class WorkoutsController {
	
	@Autowired
	private WorkoutsService workoutsService;
	
	@PostMapping("/recordWorkout")
	@CrossOrigin
	public Workouts recordWorkout(@RequestBody Workouts workout) {
		return workoutsService.recordWorkout(workout);
		
	}
	
	@GetMapping("/getWorkouts")
	@CrossOrigin
	public List<Workouts> getWorkouts(){
		return workoutsService.getWorkouts();
		
	}
	

}
