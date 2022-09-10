package com.revature.models;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

//Creating Account table (Nigel)
@Entity
@Table(name="accounts")
public class Account {
	
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY) //Generating unique Id which is also the primary key (N)
	private int accountId;
	@Column(nullable=false, unique=true)
	private String username;
	@Column(nullable=false)
	private int userPassword;
	
	//Above, making sure username and UserPassword are NOT null (N)
	
	public Account(int accountId, String username, int userPassword) {
		super();
		this.accountId = accountId;
		this.username = username;
		this.userPassword = userPassword;
	}

	public Account(String username, int userPassword) {
		super();
		this.username = username;
		this.userPassword = userPassword;
	}

	public Account() {
		super();
	}

	public int getAccountId() {
		return accountId;
	}

	public void setAccountId(int accountId) {
		this.accountId = accountId;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public int getUserPassword() {
		return userPassword;
	}

	public void setUserPassword(int userPassword) {
		this.userPassword = userPassword;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + accountId;
		result = prime * result + userPassword;
		result = prime * result + ((username == null) ? 0 : username.hashCode());
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
		Account other = (Account) obj;
		if (accountId != other.accountId)
			return false;
		if (userPassword != other.userPassword)
			return false;
		if (username == null) {
			if (other.username != null)
				return false;
		} else if (!username.equals(other.username))
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "Account [accountId=" + accountId + ", username=" + username + ", userPassword=" + userPassword + "]";
	}
	
	
	

	

	
	
	
	
	

}
