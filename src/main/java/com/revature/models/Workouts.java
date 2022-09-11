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
	private String name;
	private int cph;
	private int durMin;
	private int totCal;
	
	public Workouts() {
		super();
	}

	public Workouts(int workoutId, String name, int cph, int durMin, int totCal) {
		super();
		this.workoutId = workoutId;
		this.name = name;
		this.cph = cph;
		this.durMin = durMin;
		this.totCal = totCal;
	}

	public Workouts(String name, int cph, int durMin, int totCal) {
		super();
		this.name = name;
		this.cph = cph;
		this.durMin = durMin;
		this.totCal = totCal;
	}

	public int getWorkoutId() {
		return workoutId;
	}

	public void setWorkoutId(int workoutId) {
		this.workoutId = workoutId;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public int getCph() {
		return cph;
	}

	public void setCph(int cph) {
		this.cph = cph;
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
		result = prime * result + cph;
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
		if (cph != other.cph)
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
		return "Workouts [workoutId=" + workoutId + ", name=" + name + ", cph=" + cph + ", durMin=" + durMin
				+ ", totCal=" + totCal + "]";
	}

	

	

	

}
