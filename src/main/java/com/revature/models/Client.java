package com.revature.models;

import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;

@Entity  
@Table(name="client") //Creating main table with name of "client" (Nigel)
public class Client { //Might take OUT @Table if this doesn't work
	
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY) //Generating unique PK in serial
	private int clientId;
	@Column(nullable=false)
	private String fName;
	@Column(nullable=false)
	private String lName;
	@Column(nullable=false, unique=true)
	private String email;  //Setting all above values to NOT null
	private int caloricGoal;
	@OneToOne(fetch=FetchType.EAGER, cascade = CascadeType.ALL)
	private Account account;
	@OneToMany(fetch=FetchType.EAGER, cascade = CascadeType.ALL)
	private List<Workouts> workouts;
	//Setting relationship as OneToOne
	
	public Client() {
		super();
	}

	public Client(int clientId, String fName, String lName, String email, int caloricGoal, Account account,
			List<Workouts> workouts) {
		super();
		this.clientId = clientId;
		this.fName = fName;
		this.lName = lName;
		this.email = email;
		this.caloricGoal = caloricGoal;
		this.account = account;
		this.workouts = workouts;
	}

	public Client(String fName, String lName, String email, int caloricGoal, Account account) {
		super();
		this.fName = fName;
		this.lName = lName;
		this.email = email;
		this.caloricGoal = caloricGoal;
		this.account = account;
	}
	public int getClientId() {
		return clientId;
	}
	public void setClientId(int clientId) {
		this.clientId = clientId;
	}
	public String getfName() {
		return fName;
	}
	public void setfName(String fName) {
		this.fName = fName;
	}
	public String getlName() {
		return lName;
	}
	public void setlName(String lName) {
		this.lName = lName;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public int getCaloricGoal() {
		return caloricGoal;
	}
	public void setCaloricGoal(int caloricGoal) {
		this.caloricGoal = caloricGoal;
	}
	public Account getAccount() {
		return account;
	}
	public void setAccount(Account account) {
		this.account = account;
	}
	public List<Workouts> getWorkouts() {
		return workouts;
	}
	public void setWorkouts(List<Workouts> workouts) {
		this.workouts = workouts;
	}
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((account == null) ? 0 : account.hashCode());
		result = prime * result + caloricGoal;
		result = prime * result + clientId;
		result = prime * result + ((email == null) ? 0 : email.hashCode());
		result = prime * result + ((fName == null) ? 0 : fName.hashCode());
		result = prime * result + ((lName == null) ? 0 : lName.hashCode());
		result = prime * result + ((workouts == null) ? 0 : workouts.hashCode());
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
		Client other = (Client) obj;
		if (account == null) {
			if (other.account != null)
				return false;
		} else if (!account.equals(other.account))
			return false;
		if (caloricGoal != other.caloricGoal)
			return false;
		if (clientId != other.clientId)
			return false;
		if (email == null) {
			if (other.email != null)
				return false;
		} else if (!email.equals(other.email))
			return false;
		if (fName == null) {
			if (other.fName != null)
				return false;
		} else if (!fName.equals(other.fName))
			return false;
		if (lName == null) {
			if (other.lName != null)
				return false;
		} else if (!lName.equals(other.lName))
			return false;
		if (workouts == null) {
			if (other.workouts != null)
				return false;
		} else if (!workouts.equals(other.workouts))
			return false;
		return true;
	}
	@Override
	public String toString() {
		return "Client [clientId=" + clientId + ", fName=" + fName + ", lName=" + lName + ", email=" + email
				+ ", caloricGoal=" + caloricGoal + ", account=" + account + ", workouts=" + workouts + "]";
	}
	
	
	
	
	
	
	
	
    
}
