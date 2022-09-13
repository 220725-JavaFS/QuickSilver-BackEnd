package com.revature.models;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "workouts") // creating workouts tables
public class Workouts {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO) // auto generate workoutId number
	private int workoutId;
	private String date;
	private String name;
	private int durMin;
	private int totCal;
	
	public Workouts() {
		super();
	}

	
	public Workouts(int workoutId, String date, String name, int durMin, int totCal) {
		super();
		this.workoutId = workoutId;
		this.date = date;
		this.name = name;
		this.durMin = durMin;
		this.totCal = totCal;
	}

	public Workouts(String date, String name, int durMin, int totCal) {
		super();
		this.date = date;
		this.name = name;
		this.durMin = durMin;
		this.totCal = totCal;
	}

	public int getWorkoutId() {
		return workoutId;
	}

	public void setWorkoutId(int workoutId) {
		this.workoutId = workoutId;
	}

	public String getDate() {
		return date;
	}

	public void setDate(String date) {
		this.date = date;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public int getDurMin() {
		return durMin;
	}

	public void setDurMin(int durMin) {
		this.durMin = durMin;
	}

	public int getTotCal() {
		return totCal;
	}

	public void setTotCal(int totCal) {
		this.totCal = totCal;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((date == null) ? 0 : date.hashCode());
		result = prime * result + durMin;
		result = prime * result + ((name == null) ? 0 : name.hashCode());
		result = prime * result + totCal;
		result = prime * result + workoutId;
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Workouts other = (Workouts) obj;
		if (date == null) {
			if (other.date != null)
				return false;
		} else if (!date.equals(other.date))
			return false;
		if (durMin != other.durMin)
			return false;
		if (name == null) {
			if (other.name != null)
				return false;
		} else if (!name.equals(other.name))
			return false;
		if (totCal != other.totCal)
			return false;
		if (workoutId != other.workoutId)
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "Workouts [workoutId=" + workoutId + ", date=" + date + ", name=" + name + ", durMin=" + durMin
				+ ", totCal=" + totCal + "]";
	}

	

	

	

	

}
